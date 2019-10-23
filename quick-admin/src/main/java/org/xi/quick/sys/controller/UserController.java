package org.xi.quick.sys.controller;

import org.xi.quick.common.annotation.UpdateUser;
import org.xi.quick.common.model.PageInfoVo;
import org.xi.quick.common.model.ResponseVo;
import org.xi.quick.common.model.OrderSearch;
import org.xi.quick.common.model.OrderSearchPage;
import org.xi.quick.common.utils.VoUtils;
import org.xi.quick.common.utils.poi.ExcelUtils;
import org.xi.quick.common.validation.*;
import org.xi.quick.sys.service.UserService;
import org.xi.quick.sys.vm.addoredit.UserAddOrEditVm;
import org.xi.quick.sys.vm.detail.UserDetailVm;
import org.xi.quick.sys.vm.order.UserOrderVm;
import org.xi.quick.sys.vm.search.UserSearchVm;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.*;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * 系统用户
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@CrossOrigin
@RequestMapping("/user")
@RestController
@Validated
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 添加系统用户
     *
     * @param vm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @PostMapping("/add")
    @RequiresPermissions("sys:user:add")
    public ResponseVo<UserAddOrEditVm> add(@UpdateUser(create = true) @Validated({DataAdd.class}) @RequestBody UserAddOrEditVm vm, Errors errors) {

        userService.add(vm);
        return new ResponseVo(vm);
    }

    /**
     * 根据条件删除系统用户
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @PostMapping("/delete")
    @RequiresPermissions("sys:user:del")
    public ResponseVo<Integer> delete(@RequestBody UserSearchVm searchVm) {

        Integer count = userService.delete(searchVm);
        return new ResponseVo(count);
    }

    /**
     * 根据条件禁用系统用户
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @PostMapping("/disable")
    @RequiresPermissions("sys:user:disable")
    public ResponseVo<Integer> disable(@UpdateUser @RequestBody UserSearchVm searchVm) {

        Integer count = userService.disable(searchVm);
        return new ResponseVo(count);
    }

    /**
     * 根据条件启用系统用户
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @PostMapping("/enable")
    @RequiresPermissions("sys:user:enable")
    public ResponseVo<Integer> enable(@UpdateUser @RequestBody UserSearchVm searchVm) {

        Integer count = userService.enable(searchVm);
        return new ResponseVo(count);
    }

    /**
     * 根据条件获取系统用户实体
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @PostMapping("/get")
    @RequiresPermissions("sys:user:view")
    public ResponseVo<UserDetailVm> get(@RequestBody UserSearchVm searchVm) {

        UserDetailVm result = userService.get(searchVm);
        return new ResponseVo(result);
    }

    /**
     * 根据主键更新系统用户
     *
     * @param vm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @PostMapping("/update")
    @RequiresPermissions("sys:user:update")
    public ResponseVo<Integer> update(@UpdateUser @Validated({DataEdit.class}) @RequestBody UserAddOrEditVm vm, Errors errors) {

        int count = userService.update(vm);
        return new ResponseVo(count);
    }

    /**
     * 根据主键更新系统用户
     *
     * @param userId
     * @param password
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @PostMapping("/set-password")
    @RequiresPermissions("sys:user:set-password")
    public ResponseVo<Integer> setPassword(@RequestParam("userId") @NotNull(message = "userId (用户ID)不能为空") Integer userId,
                                      @RequestParam("password") @NotBlank(message = "password (密码)不能为空") String password) {
        int count = userService.setPassword(userId, password);
        return new ResponseVo(count);
    }

    /**
     * 根据主键获取系统用户详情
     *
     * @param userId
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @GetMapping("/detail")
    @RequiresPermissions("sys:user:view")
    public ResponseVo<UserDetailVm> getDetail(@NotNull(message = "userId (用户ID)不能为空") @RequestParam(value = "userId") Integer userId) {

        UserDetailVm detail = userService.getDetail(userId);
        detail.setPassword(null);
        detail.setSalt(null);
        return new ResponseVo(detail);
    }

    /**
     * 获取系统用户列表
     *
     * @param search
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @PostMapping("/list")
    @RequiresPermissions("sys:user:view")
    public ResponseVo<List<UserDetailVm>> getList(@RequestBody OrderSearch<UserSearchVm, UserOrderVm> search) {

        List<UserDetailVm> list = userService.getList(search);
        return new ResponseVo(list);
    }

    /**
     * 分页查询系统用户
     *
     * @param searchPage
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @PostMapping("/page-list")
    @RequiresPermissions("sys:user:view")
    public ResponseVo<PageInfoVo<UserDetailVm>> getPageInfo(@RequestBody OrderSearchPage<UserSearchVm, UserOrderVm> searchPage) {

        PageInfoVo<UserDetailVm> pageInfo = userService.getPageInfo(searchPage);
        return new ResponseVo(pageInfo);
    }

    /**
     * 导出Excel
     *
     * @param params
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @RequestMapping(value = {"/export"})
    @RequiresPermissions("sys:user:export")
    public void export(HttpServletResponse response, String params, @RequestParam(defaultValue = "", required = false) String exportName) throws IOException, IllegalAccessException {

        OrderSearch<UserSearchVm, UserOrderVm> search = VoUtils.getOrderSearch(params, UserSearchVm.class, UserOrderVm.class);
        List<UserDetailVm> list = userService.getList(search);

        String fileName = StringUtils.isBlank(exportName) ? "系统用户" : exportName;
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName + ".xlsx", "utf-8"));
        ExcelUtils.exportExcel(fileName, UserDetailVm.class, list, response.getOutputStream());
    }

}
