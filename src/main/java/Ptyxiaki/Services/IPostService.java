package Ptyxiaki.Services;

import Ptyxiaki.Dtos.PostDto;

import java.util.List;

public interface IPostService {

    List<PostDto> findAll();

    List<PostDto> findAllForManager();

    PostDto get(final Long id);

    void create(PostDto postDto);

    void update(final Long id, final PostDto postDTO);

    void delete(final Long id);
}
