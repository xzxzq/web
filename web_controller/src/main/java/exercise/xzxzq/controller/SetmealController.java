package exercise.xzxzq.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import exercise.xzxzq.constant.MessageConstant;
import exercise.xzxzq.entity.PageResult;
import exercise.xzxzq.entity.QueryPageBean;
import exercise.xzxzq.entity.Result;
import exercise.xzxzq.pojo.Setmeal;
import exercise.xzxzq.service.SetmealService;
import exercise.xzxzq.util.QiNiuUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Author : 徐志清
 * @Description :
 * @Time : 2021/1/10 19:07
 * @File : SetmealController
 */
@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    private static Logger log = LoggerFactory.getLogger(SetmealController.class);

    @Reference
    private SetmealService service;

    @PostMapping("/upload")
    public Result upload(MultipartFile imgFile) {
        String originalFilename = imgFile.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        //3. 生成唯一id,
        String uniqueId = UUID.randomUUID().toString();
        String filename = uniqueId + suffix;
        try {
            QiNiuUtils.uploadViaByte(imgFile.getBytes(), filename);
            //6. 构建返回的数据
            /*
             {
                imgName: 图片名 , 补全formData.img
                domain: 七牛的域名 图片回显imageUrl = domain+图片名
            }
             */
            Map<String, String> map = new HashMap<>(2);
            map.put("imgName", filename);
            map.put("domain", QiNiuUtils.DOMAIN);
            //7. 放到result里，再返回给页面
            return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS, map);
        } catch (IOException e) {
            log.error("上传文件失败了", e);
            return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
        }

    }

    @PostMapping("/add")
    public Result add(@RequestBody Setmeal setmeal, Integer[] checkgroupIds) {
        // 调用服务添加套餐
        service.add(setmeal, checkgroupIds);
        return new Result(true, MessageConstant.ADD_SETMEAL_SUCCESS);
    }

    @PostMapping("/page")
    public Result findPage(@RequestBody QueryPageBean queryPageBean) {
        PageResult<Setmeal> setmealPageResult = service.findPage(queryPageBean);
        return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS, setmealPageResult);
    }

    @GetMapping("/find/{id}")
    public Result findById(@PathVariable int id) {
        // 套餐信息
        Setmeal setmeal = service.findById(id);
        // 构建前端需要的数据, 还要有域名
        Map<String, Object> map = new HashMap<String, Object>(2);
        map.put("setmeal", setmeal);
        map.put("domain", QiNiuUtils.DOMAIN);
        return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS, map);
    }

    @GetMapping("/find/checkGroupIds/{id}")
    public Result findCheckGroupIdsBySetmealId(@PathVariable int id) {
        List<Integer> checkgroupIds = service.findCheckGroupIdsBySetmealId(id);
        return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS, checkgroupIds);
    }

    @PostMapping("/update")
    public Result update(@RequestBody Setmeal setmeal, Integer[] checkgroupIds) {
        // 调用服务修改套餐
        service.update(setmeal, checkgroupIds);
        return new Result(true, "编辑套餐成功");
    }

    @PostMapping("/delete/{id}")
    public Result deleteById(@PathVariable int id) {
        service.deleteById(id);
        return new Result(true, "删除套餐成功");
    }
}
