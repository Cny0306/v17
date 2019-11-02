package com.hgz.v17search.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hgz.api.ISearchService;
import com.hgz.commons.pojo.ResultBean;
import com.hgz.entity.TProduct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author huangguizhao
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @Reference
    private ISearchService searchService;

    @RequestMapping("synAllData")
    @ResponseBody
    public ResultBean synAllData(){
        return searchService.synAllData();
    }

    /**
     * 此接口适合于APP端
     * 或者Ajax异步加载数据的方式
     * @param keywords
     * @return
     */
    @RequestMapping("queryByKeyWords")
    @ResponseBody
    public ResultBean queryByKeyWords(String keywords){
        return searchService.queryByKeywords(keywords);
    }

    //log4j for
    @RequestMapping("queryByKeyWords4PC")
    public String queryByKeyWords4PC(String keywords, Model model){
        ResultBean resultBean = searchService.queryByKeywords(keywords);
        if("200".equals(resultBean.getStatusCode())){
            List<TProduct> list = (List<TProduct>) resultBean.getData();
            model.addAttribute("list",list);
        }
        return "list";
    }
}
