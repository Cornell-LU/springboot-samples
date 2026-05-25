package top.kenan.week08.like;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import top.kenan.week08.like.dto.*;
import top.kenan.week08.sms.dto.ApiResult;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 点赞、统计、排行 HTTP 接口
 */
@RestController
@RequestMapping("/api/like")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    /**
     * 点赞
     */
    @PostMapping("/contents/{contentId}")
    public ApiResult<LikeResponse> like(
            @PathVariable Long contentId,
            @RequestBody @Valid LikeRequest request) {
        LikeResponse response = likeService.like(contentId, request.getUserId(), request.getCategory());
        return ApiResult.success(response);
    }

    /**
     * 取消点赞
     */
    @DeleteMapping("/contents/{contentId}")
    public ApiResult<LikeResponse> cancelLike(
            @PathVariable Long contentId,
            @RequestBody @Valid LikeRequest request) {
        LikeResponse response = likeService.cancelLike(contentId, request.getUserId(), request.getCategory());
        return ApiResult.success(response);
    }

    /**
     * 查询用户是否已点赞某内容
     */
    @GetMapping("/contents/{contentId}/status")
    public ApiResult<LikeStatusResponse> getLikeStatus(
            @PathVariable Long contentId,
            @RequestParam @Valid Long userId) {
        boolean isLiked = likeService.isLiked(contentId, userId);
        LikeStatusResponse response = new LikeStatusResponse(contentId, userId, isLiked);
        return ApiResult.success(response);
    }

    /**
     * 查询单条内容点赞数
     */
    @GetMapping("/contents/{contentId}/count")
    public ApiResult<LikeCountResponse> getLikeCount(@PathVariable Long contentId) {
        Long count = likeService.getCount(contentId);
        LikeCountResponse response = new LikeCountResponse(contentId, count);
        return ApiResult.success(response);
    }

    /**
     * 查询内容点赞排行
     */
    @GetMapping("/ranks/{category}")
    public ApiResult<List<LikeRankResponse>> getLikeRank(
            @PathVariable String category,
            @RequestParam(defaultValue = "10") @Min(1) @Max(100) int top,
            @RequestParam(defaultValue = "desc") String order) {
        List<LikeRankResponse> rankList = likeService.getRank(category, top, order);
        return ApiResult.success(rankList);
    }

    /**
     * 参数校验异常处理
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResult<Void> onValid(MethodArgumentNotValidException e) {
        String msg = e.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("; "));
        return ApiResult.error(400, msg.isEmpty() ? "参数不合法" : msg);
    }
}