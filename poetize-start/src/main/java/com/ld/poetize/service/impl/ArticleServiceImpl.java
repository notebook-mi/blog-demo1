package com.ld.poetize.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ld.poetize.dto.ArticleDTO;
import com.ld.poetize.dto.ArticlePageDTO;
import com.ld.poetize.entity.Article;
import com.ld.poetize.mapper.ArticleMapper;
import com.ld.poetize.service.ArticleService;
import com.ld.poetize.vo.ArticleVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @Author zuosy
 * @Date 2024/1/25 20:33
 **/
@Service
@RequiredArgsConstructor
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Override
    public Page<ArticleVO> pageList(ArticlePageDTO articlePageDTO) {
        Page<Article> reqPage = Page.of(articlePageDTO.getCurrent(), articlePageDTO.getSize());
        return baseMapper.pageList(reqPage, articlePageDTO);
    }

    @Override
    public Boolean saveArticle(ArticleDTO articleDTO) {
        Long checkTitle = baseMapper.selectCount(new LambdaQueryWrapper<Article>()
                .eq(Article::getArticleTitle, articleDTO.getArticleTitle()));
        if (checkTitle > 0L){
            throw new IllegalArgumentException("该文章已存在");
        }
        Article article = BeanUtil.copyProperties(articleDTO, Article.class);
        return baseMapper.insert(article) > 0;
    }

    @Override
    public ArticleVO getArticleById(Long id) {
        Article article = baseMapper.selectById(id);
        if (Objects.isNull(article)){
            throw new IllegalArgumentException("数据异常");
        }
        return BeanUtil.copyProperties(article, ArticleVO.class);
    }

    @Override
    public Boolean updateArticle(ArticleDTO articleDTO) {
        Long checkTitle = baseMapper.selectCount(new LambdaQueryWrapper<Article>()
                .eq(Article::getArticleTitle, articleDTO.getArticleTitle())
                .ne(Article::getId, articleDTO.getId()));
        if (checkTitle > 0L){
            throw new IllegalArgumentException("该文章已存在");
        }
        Article article = BeanUtil.copyProperties(articleDTO, Article.class);
        return baseMapper.updateById(article) > 0;
    }

    @Override
    public Boolean deleteArticle(Long id) {
        return baseMapper.deleteById(id) > 0;
    }
}
