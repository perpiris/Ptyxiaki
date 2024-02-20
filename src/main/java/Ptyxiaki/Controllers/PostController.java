package Ptyxiaki.Controllers;

import Ptyxiaki.Dtos.PostDto;
import Ptyxiaki.Services.IPostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private IPostService postService;

    @GetMapping
    public String list(final Model model) {

        model.addAttribute("posts", postService.findAll());
        return "post/list";
    }

    @GetMapping("/create")
    public String create(@ModelAttribute("post") PostDto postDto) {

        return "post/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("post") @Valid PostDto postDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "post/create";
        }

        postService.create(postDto);
        redirectAttributes.addFlashAttribute("MSG_SUCCESS", "Post created successfully.");

        return "redirect:/posts";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") Long id, Model model) {

        model.addAttribute("post", postService.get(id));
        return "post/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") Long id, @ModelAttribute("post") @Valid PostDto postDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "post/edit";
        }

        postService.update(id, postDto);
        redirectAttributes.addFlashAttribute("MSG_SUCCESS", "Post updated successfully.");

        return "redirect:/posts";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") Long id, RedirectAttributes redirectAttributes) {

        postService.delete(id);
        redirectAttributes.addFlashAttribute("MSG_INFO", "Post deleted successfully.");

        return "redirect:/posts";
    }

    @GetMapping("/applications")
    public String applications(final Model model) {

        model.addAttribute("posts", postService.findAll());
        return "post/applications";
    }

    @GetMapping("/manage")
    public String manage(final Model model) {

        model.addAttribute("posts", postService.findAll());
        return "post/manage";
    }
}
