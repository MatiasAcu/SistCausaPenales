package ar.edu.unnoba.poo2021.sistcausapenales.controller;

import ar.edu.unnoba.poo2021.sistcausapenales.model.Cause;
import ar.edu.unnoba.poo2021.sistcausapenales.service.CauseService;
import ar.edu.unnoba.poo2021.sistcausapenales.service.PagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.ValidationException;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/causes")
public class CauseController {

    @Autowired
    private CauseService causeService;

    @Autowired
    private PagingService pagingService;

    @PostMapping
    public String create(Cause cause, RedirectAttributes redirectAttributes) {
        try {
            causeService.create(cause);
            redirectAttributes.addFlashAttribute("status", "success");
        } catch (ValidationException e) {
            redirectAttributes.addFlashAttribute("status", e.getMessage());
        }
        return "redirect:causes/new";
    }

    @PostMapping("/edit")
    public String update(Cause cause, RedirectAttributes redirectAttributes) {
        try {
            causeService.update(cause);
            redirectAttributes.addFlashAttribute("status", "success");
        } catch (NoSuchElementException | ValidationException e) {
            redirectAttributes.addFlashAttribute("status", e.getMessage());
        }
        redirectAttributes.addAttribute("number", cause.getNumber());
        return "redirect:/causes";
    }

    @GetMapping("/menu")
    public String viewCausesMenu() {
        return "causes/menu";
    }

    @GetMapping("/new")
    public String viewNewCause(Model model) {
        model.addAttribute("cause", new Cause());
        return "causes/new";
    }

    @GetMapping("/list")
    public String viewCauseList(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "5") int size,
            Model model) {

        Page<Cause> causesPage = causeService.getCausesPage(page - 1, size);
        model.addAttribute("causes", causesPage);

        int totalPages = causesPage.getTotalPages();
        model.addAttribute("totalPages", totalPages);

        List<Integer> pageList = pagingService.getPagingRange(page, totalPages, 5);
        model.addAttribute("pages", pageList);
        model.addAttribute("currentPage", page);

        return "causes/list";
    }

    @GetMapping()
    public String viewUserInfo(@RequestParam String number,
                               @RequestParam(required = false, defaultValue = "false") boolean edit, Model model) {
        try {
            model.addAttribute("cause", causeService.getCause(number).get());
            model.addAttribute("edit", edit);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cause not found");
        }
        return "causes/info";
    }
}
