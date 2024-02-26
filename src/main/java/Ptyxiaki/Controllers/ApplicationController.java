package Ptyxiaki.Controllers;

import Ptyxiaki.Dtos.ApplicationDto;
import Ptyxiaki.Services.IApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/applications")
public class ApplicationController {

    @Autowired
    private IApplicationService applicationService;

    @GetMapping("/manage")
    public String manage(@RequestParam(defaultValue = "0") int page,
                         @RequestParam(defaultValue = "10") int size,
                         @RequestParam(defaultValue = "id") String sortBy,
                         final Model model) {

        Page<ApplicationDto> postPage = applicationService.findAllForApplicant(page, size, sortBy);
        model.addAttribute("applications", postPage.getContent());
        model.addAttribute("currentPage", postPage.getNumber());
        model.addAttribute("totalPages", postPage.getTotalPages());

        return "application/manage";
    }
}
