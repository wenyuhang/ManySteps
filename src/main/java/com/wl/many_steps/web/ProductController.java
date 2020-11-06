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
import org.springframework.beans.factory.annotation.Value;
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
@Validated
@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @Value("${pro.img.path}")
    private String path;


    /**
     * 添加商品
     *
     * @return
     */
    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    public ApiResponse add(@NotBlank(message = "name 不能为空") String name,
                           @NotBlank(message = "coin 不能为空") @Min(value = 0, message = "coin 不能小于0") String coin,
                           @NotBlank(message = "energy 不能为空") @Min(value = 0, message = "energy 不能小于0") String energy,
                           @NotBlank(message = "price 不能为空") @Min(value = 0, message = "price 不能小于0") String price,
                           @NotBlank(message = "stock 不能为空") @Min(value = 0, message = "stock 不能小于0") String stock,
                           @NotBlank(message = "subTitle 不能为空") String subTitle,
                           MultipartFile image, HttpServletRequest request) {

        //查询是否存在相同名称商品
        Product product = productService.get(name);
        if (null != product) {
            return ApiResponse.of(999, "该商品已存在", null);
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
        product.setCoin(Float.parseFloat(coin));
        product.setEnergy(Float.parseFloat(energy));
        product.setPrice(Float.parseFloat(price));
        product.setStock(Integer.parseInt(stock));
        product.setSubTitle(subTitle);
        product.setImageurl(imagePath);
        product.setCreatedate(String.valueOf(System.currentTimeMillis()));
        int code = productService.add(product);
        if (code==0){
            return ApiResponse.of(999,"操作失败请重试",null);
        }
        return ApiResponse.ofSuccess(product);
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public ApiResponse sss(String username,String password) {
        System.out.println(username+"============"+password);
        return ApiResponse.of(10000,"操作成功",null);
    }

    /**
     * 删除商品
     *
     * @param id 商品id
     * @return
     */
    @RequestMapping(value = "/deleteProduct", method = RequestMethod.POST)
    public ApiResponse delete(@NotBlank(message = "id 不能为空") @Min(value = 0, message = "id 不能小于0") String id, HttpServletRequest request) {
        //查询是否存在相同名称商品
        Product product = productService.get(Integer.parseInt(id));
        if (null == product) {
            return ApiResponse.of(999, "该商品不存在", null);
        }
        productService.delete(Integer.parseInt(id));
        //删除商品图片
        deleteImageFile(product.getImageurl().replace("img/product/", ""), request);
        return ApiResponse.ofMessage("删除成功");
    }

    /**
     * 修改商品
     *
     * @return
     */
    @RequestMapping(value = "/updateProduct", method = RequestMethod.POST)
    public ApiResponse update(@NotBlank(message = "id 不能为空") @Min(value = 0, message = "id 不能小于0") String id,
                              @NotBlank(message = "name 不能为空") String name,
                              @NotBlank(message = "coin 不能为空") @Min(value = 0, message = "coin 不能小于0") String coin,
                              @NotBlank(message = "energy 不能为空") @Min(value = 0, message = "energy 不能小于0") String energy,
                              @NotBlank(message = "price 不能为空") @Min(value = 0, message = "price 不能小于0") String price,
                              @NotBlank(message = "stock 不能为空") @Min(value = 0, message = "stock 不能小于0") String stock,
                              @NotBlank(message = "subTitle 不能为空") String subTitle,
                              MultipartFile image, HttpServletRequest request) {
        //查询是否存在相同名称商品
        Product product = productService.get(Integer.parseInt(id));
        if (null == product) {
            return ApiResponse.of(999, "该商品不存在，请修改后重试", null);
        }
        //校验image
        if (null!=image && !image.isEmpty() && image.getSize() > 0) {
            String imagePath = saveOrUpdateImageFile(image, request);
            if (TextUtils.isEmpty(imagePath)) {
                return ApiResponse.of(999, "图片文件处理失败，请重试", null);
            }
            //删除之前的图片
            deleteImageFile(product.getImageurl().replace("img/product/", ""), request);
            product.setImageurl(imagePath);
        }
        product.setName(name);
        product.setCoin(Float.parseFloat(coin));
        product.setEnergy(Float.parseFloat(energy));
        product.setPrice(Float.parseFloat(price));
        product.setStock(Integer.parseInt(stock));
        product.setSubTitle(subTitle);
        product.setCreatedate(String.valueOf(System.currentTimeMillis()));

        int code = productService.updata(product);
        if (code==0){
            return ApiResponse.of(999,"操作失败请重试",null);
        }
        return ApiResponse.ofSuccess(product);
    }

    /**
     * 获取商品列表
     *
     * @param pageBean
     * @return
     */
    @RequestMapping(value = "/productList", method = RequestMethod.POST)
    public ApiResponse list(@Validated @RequestBody PageBean pageBean) {
        PageHelper.startPage(pageBean.getPage(), pageBean.getSize());
        List<Product> list = productService.list();
        PageInfo pageInfo = new PageInfo(list);
        return ApiResponse.ofSuccess(pageInfo);
    }

    /**
     * 根据id获取商品
     * @param bean
     * @return
     */
    @RequestMapping(value = "/getProduct", method = RequestMethod.POST)
    public ApiResponse list(@Validated @RequestBody Product bean) {
        Product product = productService.get(bean.getId());
        if (null == product) {
            return ApiResponse.of(999, "该商品不存在，请修改后重试", null);
        }
        return ApiResponse.ofSuccess(product);
    }



    /**
     * 保存图片
     *
     * @param image
     * @param request
     * @return
     */
    public String saveOrUpdateImageFile(MultipartFile image, HttpServletRequest request) {
        String filePath = null;
        try {
//            File imageFolder = new File(request.getServletContext().getRealPath("img/product"));
            System.out.println(path);
            File imageFolder = new File(path);
            System.out.println(imageFolder.getAbsolutePath());
            File file = new File(imageFolder, System.currentTimeMillis() + ".jpg");
            System.out.println(file.getAbsolutePath());
            if (!file.getParentFile().exists())
                file.getParentFile().mkdirs();
            image.transferTo(file);
            BufferedImage img = ImageUtil.change2jpg(file);
            ImageIO.write(img, "jpg", file);
            filePath = "img/product/" + file.getName();
            System.out.println(file.getAbsolutePath());
        } catch (IOException e) {
            filePath = null;
            System.out.println(e);
        }
        return filePath;
    }

    /**
     * 删除图片
     *
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
