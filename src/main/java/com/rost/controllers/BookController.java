package com.rost.controllers;

import com.rost.dao.BookDAO;
import com.rost.models.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookDAO bookDAO;

    public BookController(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @GetMapping("/new")
    public String createBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String createBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        //if(bindingResult.hasErrors()) {}
        bookDAO.createBook(book);
        return "redirect:/books/" + bookDAO.getCurrentMaxId();
    }

    @GetMapping()
    public String readBooks(Model model) {
        model.addAttribute("books", bookDAO.readBooks());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String readBook(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDAO.readBookById(id));
        return "books/show";
    }

    @GetMapping("/edit/{id}")
    public String updateBook(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDAO.readBookById(id));
        return "books/edit";
    }

    @PatchMapping()
    public String updateBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        //if (bindingResult.hasErrors()) {}
        bookDAO.updateBook(book);
        return "redirect:/books/" + book.getId();
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        bookDAO.deleteBookById(id);
        return "redirect:/books";
    }
}
