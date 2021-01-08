package exercise.xzxzq.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import exercise.xzxzq.constant.ErrorLogConstant;
import exercise.xzxzq.constant.MessageConstant;
import exercise.xzxzq.constant.PageConstant;
import exercise.xzxzq.entity.PageResult;
import exercise.xzxzq.entity.QueryPageBean;
import exercise.xzxzq.entity.Result;
import exercise.xzxzq.exception.UnsupportedArgumentException;
import exercise.xzxzq.pojo.CheckItem;
import exercise.xzxzq.service.CheckItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author : 徐志清
 * @Description :
 * @Time : 2021/1/6 9:08
 * @File : CheckItemController
 */
@RestController
@RequestMapping("/checkitem")
public class CheckItemController {
    @Reference
    private CheckItemService service;

    @GetMapping("/all")
    public Result findAll() {
        // 调用服务查询
        List<CheckItem> list = service.findAll();
        // 封装到Result再返回
        return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, list);
    }

    @PostMapping("/add")
    public Result add(@RequestBody CheckItem checkItem) {
        // 调用服务添加
        service.add(checkItem);
        // 返回操作的结果
        return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
    }

    @GetMapping("/page/{pageSize}/{currentPage}")
    public Result getCheckItemPage(@PathVariable Integer pageSize,@PathVariable Integer currentPage) {
        QueryPageBean query = new QueryPageBean();
        query.setCurrentPage(currentPage);
        query.setPageSize(pageSize);
        if (pageSize > PageConstant.CHECKITEM_PAGESIZE_MAX || pageSize < PageConstant.CHECKITEM_PAGESIZE_MIN) {
            throw new UnsupportedArgumentException(ErrorLogConstant.OUT_OF_RANGE);
        }
        PageResult<CheckItem> page = service.getCheckItemPage(query);
        return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,page);
    }

    @GetMapping("/find/id/{id}")
    public Result findById(@PathVariable Integer id){
        CheckItem checkItem = service.findById(id);
        return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItem);
    }

    @PostMapping("/update")
    public Result update(@RequestBody CheckItem checkItem){
        // 调用服务更新
        service.update(checkItem);
        // 返回操作的结果
        return new Result(true, MessageConstant.EDIT_CHECKITEM_SUCCESS);
    }

    @PostMapping("/delete/id/{id}")
    public Result deleteById(@PathVariable Integer id){
        service.deleteById(id);
        return new Result(true, MessageConstant.DELETE_CHECKITEM_SUCCESS);
    }

}
