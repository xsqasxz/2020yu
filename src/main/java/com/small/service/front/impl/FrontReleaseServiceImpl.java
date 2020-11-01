package com.small.service.front.impl;

import com.small.constant.FrontState;
import com.small.constant.ToConfigure;
import com.small.dto.blog.BlogDto;
import com.small.dto.front.FrontReleaseDto;
import com.small.entity.JsonResponse;
import com.small.entity.front.FrontHtml;
import com.small.mapper.front.FrontHtmlMapper;
import com.small.service.front.FrontReleaseService;
import com.small.vo.blog.DetailedVo;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.annotation.Resource;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author xueshiqi
 * @since 2020/10/14
 * 文章发布
 */
@Service
public class FrontReleaseServiceImpl implements FrontReleaseService {

    @Resource
    private FrontHtmlMapper frontHtmlMapper;
    @Autowired
    private SpringTemplateEngine springTemplateEngine;
    @Autowired
    private ToConfigure toConfigure;


    @Override
    public JsonResponse saveFrontRelease(FrontReleaseDto frontReleaseDto) {
        FrontHtml frontHtml = new FrontHtml(frontReleaseDto);
        if(frontHtml!=null && frontHtml.getId()!=null){
            frontHtmlMapper.updateByPrimaryKeySelective(frontHtml);
        }else {
            frontHtmlMapper.insert(frontHtml);
        }
        return JsonResponse.ok("模版保存成功！");
    }

    @Override
    public FrontHtml selectByPrimaryKey(FrontReleaseDto frontReleaseDto) {
        return frontHtmlMapper.selectByPrimaryKey(new FrontHtml(frontReleaseDto));
    }

    @Override
    public FrontHtml selectByPrimaryKey(BlogDto blogDto) {
        return frontHtmlMapper.selectByPrimaryKey(new FrontHtml(blogDto));
    }

    @Override
    public JsonResponse deleteFrontHtml(FrontReleaseDto frontReleaseDto) {
        frontHtmlMapper.deleteByPrimaryKey(frontReleaseDto.getFrontReleaseId());
        return JsonResponse.ok("模版删除成功！");
    }

    @Override
    public JsonResponse crdateHomeHtml() {
        //查询出首页信息
        FrontHtml frontHtml = new FrontHtml();
        frontHtml.setHtmlType(FrontState.HTML_TYPE_HOME);
        frontHtml.setTakeEffect(true);
        List<FrontHtml> list= frontHtmlMapper.select(frontHtml);
        Context context = new Context();
        context.setVariable("htmlList",list);
        context.setVariable("urlPath",toConfigure.getUrlPath());
        genHtmlPage("index",context,"blog/index-template");
        list.parallelStream().forEach(fh->frontHtmlMapper.updateByPrimaryKeySelective(new FrontHtml(fh.getId(),false)));
        return JsonResponse.ok("更新首页成功！");
    }

    @Override
    public JsonResponse crdateDetailedHtml() {
        //先查询出需要更新的类型，在将类型进行排序
        List<DetailedVo> list= frontHtmlMapper.getDetailed(null);
        //对数据进行分组
        Map<String,List<DetailedVo>> map= list.parallelStream().collect(Collectors.groupingBy(DetailedVo::getHtmlKeyword));
        //将分组后的数据进行生成
        Context context = new Context();
        context.setVariable("urlPath",toConfigure.getUrlPath());
        map.forEach((k, v) -> {
            context.setVariable("detailedVoList",v);
            genHtmlPage(k,context,"blog/detailed-template");
        });
        return JsonResponse.ok("更新话题成功！");
    }

    @Override
    public JsonResponse crdatedetailsHtml() {
        FrontHtml frontHtml = new FrontHtml();
        frontHtml.setHtmlType(FrontState.HTML_TYPE_DETAILS);
        frontHtml.setTakeEffect(true);
        frontHtml.setWantUpdate(true);
        List<FrontHtml> list= frontHtmlMapper.select(frontHtml);
        Context context = new Context();
        list.forEach(fh->{
            context.setVariable("htmlText",fh.getHtmlText());
            genHtmlPage("details"+fh.getId(),context,"blog/blog-details-template");
        });
        list.parallelStream().forEach(fh->frontHtmlMapper.updateByPrimaryKeySelective(new FrontHtml(fh.getId(),false)));
        return JsonResponse.ok("更新详情页面成功");
    }

    /**
     * 生成页面
     * @param htmlName
     * @param context
     * @param viewName
     */
    private void genHtmlPage(Object htmlName,Context context,String viewName) {
        FileWriter fileWriter = null;
        try {
            StringBuffer stringBuffer = new StringBuffer(toConfigure.getNginxPath());
            stringBuffer.append(htmlName).append(FrontState.HTML_ADDRESS_SUFFIX);
            fileWriter = new FileWriter(stringBuffer.toString());
            //将thymeleaf模板生成的结果存入静态文件中
            springTemplateEngine.process(viewName, context, fileWriter);
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            IOUtils.closeQuietly(fileWriter);
        }
    }
}
