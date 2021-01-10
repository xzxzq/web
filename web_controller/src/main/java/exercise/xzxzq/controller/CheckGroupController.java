package exercise.xzxzq.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import exercise.xzxzq.constant.MessageConstant;
import exercise.xzxzq.entity.PageResult;
import exercise.xzxzq.entity.QueryPageBean;
import exercise.xzxzq.entity.Result;
import exercise.xzxzq.pojo.CheckGroup;
import exercise.xzxzq.service.CheckGroupService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author : 徐志清
 * @Description :
 * @Time : 2021/1/9 9:06
 * @File : CheckGroupController
 */
@RestController
@RequestMapping("/checkgroup")
public class CheckGroupController {

    @Reference
    private CheckGroupService service;

    @PostMapping("/add")
    public Result add(@RequestBody CheckGroup checkgroup, Integer[] checkitemIds) {
        // 调用服务 添加检查组
        service.add(checkgroup, checkitemIds);
        return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);
    }

    /**
     * 检查组的分页查询
     *
     * @param queryPageBean
     * @return
     */
    @PostMapping("/page")
    public Result findPage(@RequestBody QueryPageBean queryPageBean) {
        // 调用服务 分页查询
        PageResult<CheckGroup> pageResult = service.findPage(queryPageBean);
        return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS, pageResult);
    }

    /**
     * 通过id查询检查组
     */
    @GetMapping("/find/{id}")
    public Result findById(@PathVariable int id) {
        CheckGroup checkGroup = service.findById(id);
        return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS, checkGroup);
    }

    /**
     * 通过检查组id查询选中的检查项id
     *
     * @param id
     * @return
     */
    @GetMapping("/find/checkItemIds/{CheckGroupId}")
    public Result findCheckItemIdsByCheckGroupId(@PathVariable("CheckGroupId") int id) {
        // 通过检查组id查询选中的检查项id集合
        List<Integer> checkItemIds = service.findCheckItemIdsByCheckGroupId(id);
        return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, checkItemIds);
    }

    /**
     * 修改检查组
     *
     * @param checkgroup   检查组信息
     * @param checkitemIds 选中的检查项id数组
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody CheckGroup checkgroup, Integer[] checkitemIds) {
        // 调用服务 修改检查组
        service.update(checkgroup, checkitemIds);
        return new Result(true, MessageConstant.EDIT_CHECKGROUP_SUCCESS);
    }

    /**
     * 通过id删除检查组
     *
     * @param id
     * @return
     */
    @PostMapping("/delete")
    public Result deleteById(@PathVariable int id) {
        // 调用服务删除
        service.deleteById(id);
        return new Result(true, MessageConstant.DELETE_CHECKGROUP_SUCCESS);
    }

    /**
     * 查询所有的检查组
     *
     * @return
     */
    @GetMapping("/all")
    public Result findAll() {
        List<CheckGroup> list = service.findAll();
        return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS, list);
    }
}
