import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.kenan.week06.entity.Special;

public interface SpecialService {

    /**
     * 根据标题获取专栏（分页）
     *
     * @param title    标题
     * @param pageNum  页码
     * @param pageSize 页大小
     * @return 专栏列表
     */
    Page<Special> selectByTitle(String title, int pageNum, int pageSize);
}