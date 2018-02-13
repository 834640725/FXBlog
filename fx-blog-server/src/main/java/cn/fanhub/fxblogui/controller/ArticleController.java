package cn.fanhub.fxblogui.controller;

import cn.fanhub.fxblogui.entity.Article;
import cn.fanhub.fxblogui.manager.ArticleManger;
import cn.fanhub.fxblogui.model.ArticleDigestVO;
import cn.fanhub.fxblogui.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author chengfan
 * @date 2018-2-8 22:16:38
 */
@RequestMapping("/article")
@RestController
public class ArticleController {
    @Autowired
    private ArticleManger articleManger;

    /**
     * Gets article.
     *
     * @param id the id
     * @return the article
     */
    @GetMapping("/{id}")
    public Result<Article> getArticle(@PathVariable long id) {
        return Result.of(articleManger.getOne(id));
    }

    /**
     * Gets articles.
     *
     * @return the articles
     */
    @GetMapping("/all")
    public Result<List<Article>> getArticles() {
        return Result.of(articleManger.getAll());
    }

    /**
     * Gets articles by tag.
     *
     * @param tagName the tag name
     * @return the articles by tag
     */
    @GetMapping("/tag/{tagName}")
    public Result<List<Article>> getArticlesByTag(@PathVariable String tagName) {
        return Result.of(articleManger.getByTagName(tagName));
    }

    /**
     * Gets articles by categories.
     *
     * @param categoriesName the categories name
     * @return the articles by categories
     */
    @GetMapping("/categories/{categoriesName}")
    public Result<List<Article>> getArticlesByCategories(@PathVariable String categoriesName) {
        return Result.of(articleManger.getByCategoriesName(categoriesName));
    }

    /**
     * Gets article digests.
     *
     * @param pageNum the page num
     * @return the article digests
     */
    @GetMapping("/digests/page/{pageNum}")
    public Result<List<ArticleDigestVO>> getArticleDigests(@PathVariable int pageNum) {
        PageRequest pageRequest = new PageRequest(pageNum, 10);
        return Result.of(articleManger.getArticleDigests(pageRequest));
    }

    /**
     * Update article result.
     *
     * @param article the article
     * @return the result
     */
    @PutMapping
    public Result<Article> updateArticle(@RequestBody Article article) {
        return Result.of(articleManger.update(article));
    }

    /**
     * Add result.
     *
     * @param article the article
     * @return the result
     */
    @PostMapping
    public Result<Article> add(@RequestBody Article article){
        return Result.of(articleManger.save(article));
    }
}