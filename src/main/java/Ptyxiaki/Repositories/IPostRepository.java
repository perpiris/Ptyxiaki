package Ptyxiaki.Repositories;

import Ptyxiaki.Entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPostRepository extends JpaRepository<Post, Long> {

}
