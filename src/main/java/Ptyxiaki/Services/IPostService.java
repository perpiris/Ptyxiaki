package Ptyxiaki.Services;

import Ptyxiaki.Dtos.PostDto;
import org.springframework.data.domain.Page;

public interface IPostService {

    Page<PostDto> findAll(int pageNumber, int pageSize, String sortBy);

    Page<PostDto> findAllForManager(int pageNumber, int pageSize, String sortBy);

    PostDto get(final Long id);

    void create(PostDto postDto);

    void update(final Long id, final PostDto postDTO);

    void delete(final Long id);
}
