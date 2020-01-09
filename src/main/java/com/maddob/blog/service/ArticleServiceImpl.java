package com.maddob.blog.service;

import com.maddob.blog.domain.Article;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.maddob.blog.api.ArticleDTO;
import com.maddob.blog.api.ArticlePageDTO;
import com.maddob.blog.mappers.ArticleMapper;
import com.maddob.blog.repositories.ArticleRepository;

/**
 * Article service implementation
 * <p>
 * Gives access to the article information
 *
 * @author Martin Dobrev
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    public final static int DEFAULT_ARTICLE_PAGE_SIZE = 5;

    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

    public ArticleServiceImpl(final ArticleRepository articleRepository, final ArticleMapper articleMapper) {
        this.articleRepository = articleRepository;
        this.articleMapper = articleMapper;
    }

    @Override
    public ArticlePageDTO getArticlePage(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return articleMapper.domainPage2dto(articleRepository.findByPublishedTrue(pageable));
    }

    @Override
    public ArticlePageDTO getArticlePage(int pageNumber) {
        return this.getArticlePage(pageNumber, DEFAULT_ARTICLE_PAGE_SIZE);
    }

    @Override
    public ArticleDTO getArticle(Long id) {
        if (!articleRepository.existsById(id)) {
            return null;
        }

        return articleMapper.domain2dto(articleRepository.findById(id).get());
    }

    @Override
    public ArticleDTO createArticle(ArticleDTO article) {
        Article articleDomain = articleMapper.dto2domain(article);
        Article save = articleRepository.save(articleDomain);
        return articleMapper.domain2dto(save);
    }

    @Override
    public void deleteArticle(Long id) {
        ArticleDTO article = getArticle(id);
        if (article != null) {
            articleRepository.delete(articleMapper.dto2domain(article));
        }
    }

//    @Override
//    public ArticleDTO editArticle(ArticleDTO article, Long articleId) {
//        if (getArticle(articleId) == null) {
//            return null;
//        }
//        Article save = articleRepository.save(articleMapper.dto2domain(article));
//        return articleMapper.domain2dto(save);
//
//    }
}
