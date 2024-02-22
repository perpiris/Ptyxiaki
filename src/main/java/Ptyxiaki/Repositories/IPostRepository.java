package Ptyxiaki.Repositories;

import Ptyxiaki.Entities.AppUser;
import Ptyxiaki.Entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByCreatedBy(AppUser user, Pageable pageable);
}
