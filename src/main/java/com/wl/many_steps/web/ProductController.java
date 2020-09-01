package com.wl.many_steps.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wl.many_steps.model.ApiResponse;
import com.wl.many_steps.pojo.PageBean;
import com.wl.many_steps.pojo.Product;
import com.wl.many_steps.service.ProductService;
import com.wl.many_steps.utils.ImageUtil;
import org.apache.http.util.TextUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Min;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/9/1 15:23
 * desc   : 商品管理
 */
@RestController
@RequestMapping(value = "product")
@Validated
public class ProductController {

    @Autowired
    ProductService productService;


    /**
     * 添加商品
     * @return
     */
    @RequestMapping(value = "/addProduct",method = RequestMethod.POST)
    public ApiResponse add(@NotBlank(message = "name 不能为空") String name,
                           @NotBlank(message = "coin 不能为空")@Min(value = 0,message = "coin 不能小于0") String coin,
                           @NotBlank(message = "price 不能为空")@Min(value = 0,message = "price 不能小于0") String price,
                           @NotBlank(message = "stock 不能为空")@Min(value = 0,message = "stock 不能小于0") String stock,
                           @NotBlank(message = "subTitle 不能为空") String subTitle,
                           @NotBlank(message = "convertsteps 不能为空")@Min(value = 0,message = "convertsteps 不能小于0") String convertsteps,
                           MultipartFile image, HttpServletRequest request){

        //查询是否存在相同名称商品
        Product product = productService.get(name);
        if (null!=product){
            return ApiResponse.of(999,"该商品已存在",null);
        }
        //校验image
        if (image.isEmpty() || image.getSize() <= 0) {
            return ApiResponse.of(999, "请添加图片文件", null);
        }
        String imagePath = saveOrUpdateImageFile(image, request);
        if (TextUtils.isEmpty(imagePath)) {
            return ApiResponse.of(999, "图片文件处理失败，请重试", null);
        }
        //创建商品对象
        product = new Product();
        product.setName(name);
        product.setCoin(Integer.parseInt(coin));
        product.setPrice(Float.parseFloat(price));
        product.setStock(Integer.parseInt(stock));
        product.setSubTitle(subTitle);
        product.setImageurl(imagePath);
        product.setConvertsteps(Integer.parseInt(convertsteps));
        product.setCreatedate(String.valueOf(System.currentTimeMillis()));
        int pid = productService.add(product);
        return ApiResponse.ofSuccess(product);
    }
    /**
     * 获取商品列表
     * @param pageBean
     * @return
     */
    @RequestMapping(value = "/productList",method = RequestMethod.POST)
    public ApiResponse list(@Validated @RequestBody PageBean pageBean){
        PageHelper.startPage(pageBean.getPage(),pageBean.getSize());
        List<Product> list = productService.list();
        System.out.println("list is size:"+list.size());
        PageInfo pageInfo=new PageInfo(list);
        return ApiResponse.ofSuccess(pageInfo);
    }


    /**
     * 保存图片
     * @param image
     * @param request
     * @return
     */
    public String saveOrUpdateImageFile(MultipartFile image, HttpServletRequest request) {
        String filePath = null;
        try {
            File imageFolder = new File(request.getServletContext().getRealPath("img/product"));
            File file = new File(imageFolder,    System.currentTimeMillis() + ".jpg");
            if (!file.getParentFile().exists())
                file.getParentFile().mkdirs();
            image.transferTo(file);
            BufferedImage img = ImageUtil.change2jpg(file);
            ImageIO.write(img, "jpg", file);
            filePath = "img/product/" + file.getName();
            System.out.println(file.getAbsolutePath());
        } catch (IOException e) {
            filePath = null;
        }
        return filePath;
    }

    /**
     * 删除图片
     * @param path
     * @param request
     * @return
     */
    public boolean deleteImageFile(String path, HttpServletRequest request) {
        String realPath = request.getServletContext().getRealPath("img/product/") + path;
        File file = new File(realPath);
        boolean delete = file.delete();
        return delete;
    }
}
