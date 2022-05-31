package ar.edu.unnoba.poo2021.sistcausapenales.controller;

import ar.edu.unnoba.poo2021.sistcausapenales.model.User;
import ar.edu.unnoba.poo2021.sistcausapenales.service.PagingService;
import ar.edu.unnoba.poo2021.sistcausapenales.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.ValidationException;
import java.util.List;


@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PagingService pagingService;

    @PostMapping
    public String create(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        try {
            userService.create(user);
            redirectAttributes.addFlashAttribute("status", "success");
        } catch (ValidationException e) {
            redirectAttributes.addFlashAttribute("user", user);
            redirectAttributes.addFlashAttribute("status", e.getMessage());
        }
        return "redirect:users/new";
    }

    @GetMapping("/menu")
    public String viewUserMenu() {
        return "users/menu";
    }

    @GetMapping("/new")
    public String viewNewUser(Model model,
                              @RequestAttribute(name = "user", required = false) User user) {
        if (user == null) {
            model.addAttribute("user", new User());
        }
        return "users/new";
    }

    @GetMapping("/list")
    public String viewUserList(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "5") int size,
            Model model) {

        Page<User> usersPage = userService.getUsersPage(page - 1, size);
        model.addAttribute("users", usersPage);

        int totalPages = usersPage.getTotalPages();
        model.addAttribute("totalPages", totalPages);

        List<Integer> pageList = pagingService.getPagingRange(page, totalPages, 5);
        model.addAttribute("pages", pageList);
        model.addAttribute("currentPage", page);

        return "users/list";
    }


    @GetMapping("/delete")
    public String delete(@RequestParam String email,
                         Authentication authentication,
                         RedirectAttributes redirectAttributes) {

        User sessionUser = (User) authentication.getPrincipal();

        try {
            userService.delete(sessionUser, email);
        } catch (ValidationException e) {
            redirectAttributes.addFlashAttribute("invalid", true);
        }

        return "redirect:/users/list";
    }
}
