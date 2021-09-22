package org.securedev.tasklistback.controllers;

import org.securedev.tasklistback.models.Tasks;
import org.securedev.tasklistback.models.User;
import org.securedev.tasklistback.repositories.TasksRepository;
import org.securedev.tasklistback.services.CustomUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
public class TasksController {

    @Autowired
    private CustomUserDetailsService userService;

    @Autowired
    private TasksRepository tasksRepository;

    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public ModelAndView tasks() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("tasks", tasksRepository.findAll());
        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("fullName", "Welcome " + user.getFullname());
        modelAndView.addObject("adminMessage", "Content requires admin rights");

        modelAndView.setViewName("tasks");
        return modelAndView;
    }

    @RequestMapping("/tasks/create")
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("fullName", "Welcome " + user.getFullname());
        modelAndView.addObject("adminMessage", "Content requires admin rights");

        modelAndView.setViewName("create");
        return modelAndView;
    }

    @RequestMapping("/tasks/save")
    public String save(@RequestParam String description, @RequestParam("due") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date due) {
       
        Tasks task = new Tasks();
        task.setDescription(description);
        task.setDue(due);
        task.setUpdated(new Date());
        tasksRepository.save(task);

        return "redirect:/tasks/show/" + task.getId();
    }

    @RequestMapping("/tasks/show/{id}")
    public ModelAndView show(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("fullName", "Welcome " + user.getFullname());
        modelAndView.addObject("adminMessage", "Content requires admin rights");
        modelAndView.addObject("task", tasksRepository.findById(id).orElse(null));
        modelAndView.setViewName("show");
        return modelAndView;
    }

    @RequestMapping("/tasks/delete")
    public String delete(@RequestParam Long id) {
        Tasks task = tasksRepository.findById(id).orElse(null);
        tasksRepository.delete(task);

        return "redirect:/tasks";
    }

    @RequestMapping("/tasks/edit/{id}")
    public ModelAndView edit(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("fullName", "Welcome " + user.getFullname());
        modelAndView.addObject("adminMessage", "Content requires admin rights");
        modelAndView.addObject("task", tasksRepository.findById(id).orElse(null));
        modelAndView.setViewName("edit");
        return modelAndView;
    }

    @RequestMapping("/tasks/update")
    public String update(@RequestParam Long id, @RequestParam String description, @RequestParam("due") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date due) {
        Tasks task = tasksRepository.findById(id).orElse(null);
        task.setDescription(description);
        task.setDue(due);
        task.setUpdated(new Date());
        tasksRepository.save(task);

        return "redirect:/tasks/show/" + task.getId();
    }

}
