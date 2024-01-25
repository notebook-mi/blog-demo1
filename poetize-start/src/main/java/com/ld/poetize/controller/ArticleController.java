package com.ld.poetize.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ld.poetize.dto.ArticleDTO;
import com.ld.poetize.dto.ArticlePageDTO;
import com.ld.poetize.service.ArticleService;
import com.ld.poetize.utils.web.R;
import com.ld.poetize.vo.ArticleVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author zuosy
 * @Date 2024/1/25 20:36
 **/
@Tag(name = "文章信息")
@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping("/list")
    @Operation(summary = "分页数据列表")
    @PreAuthorize("hasAuthority('SCOPE_administrator')")
    public R<Page<ArticleVO>> pageList(@RequestBody ArticlePageDTO articlePageDTO){
        return R.okForData(articleService.pageList(articlePageDTO));
    }

    @PostMapping("/saveArticle")
    @Operation(summary = "新增文章")
    @PreAuthorize("hasAuthority('SCOPE_administrator')")
    public R<Boolean> saveArticle(@RequestBody @Validated(Insert.class) ArticleDTO articleDTO){
        return R.okForData(articleService.saveArticle(articleDTO));
    }

    @GetMapping("/getArticleById/{id}")
    @Operation(summary = "文章详情")
    @PreAuthorize("hasAuthority('SCOPE_administrator')")
    public R<ArticleVO> getArticleById(@PathVariable("id") Long id){
        return R.okForData(articleService.getArticleById(id));
    }

    @PostMapping("/updateArticle")
    @Operation(summary = "修改文章")
    @PreAuthorize("hasAuthority('SCOPE_administrator')")
    public R<Boolean> updateArticle(@RequestBody @Validated(Update.class)ArticleDTO articleDTO){
        return R.okForData(articleService.updateArticle(articleDTO));
    }

    @GetMapping("/deleteArticle/{id}")
    @Operation(summary = "删除文章")
    @PreAuthorize("hasAuthority('SCOPE_administrator')")
    public R<Boolean> deleteArticle(@PathVariable("id") Long id){
        return R.okForData(articleService.deleteArticle(id));
    }
}