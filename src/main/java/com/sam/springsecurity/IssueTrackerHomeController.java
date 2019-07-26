package com.sam.springsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class IssueTrackerHomeController {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    IssueRepository issueRepository;

    @RequestMapping("/index")
    public String home(Model model){
        model.addAttribute("projects", projectRepository.findAll());
        model.addAttribute("issues", issueRepository.findAll());

        return "index";
    }

    @GetMapping("/addproject")
    public String projectform(Model model){
        model.addAttribute("project", new Project());
        return "projectform";
    }

    @PostMapping("/process_project")
    public String processProjectForm(@Valid Project project, BindingResult result){
        if (result.hasErrors()){
            return "projectform";
        }
        projectRepository.save(project);

        return "redirect:/projectlist";
    }

    @RequestMapping("/projectlist")
    public String projectList(Model model){
        model.addAttribute("projects", projectRepository.findAll());

        return "projectlist";
    }

    @GetMapping("/addissue")
    public String issueForm(Model model){
        model.addAttribute("projects", projectRepository.findAll());
        model.addAttribute("issue", new Issue());
        return "issueform";
    }

    @PostMapping("/process_issue")
    public String processIssueForm(@Valid Issue issue, BindingResult result){
        if (result.hasErrors()){
            return "issueform";
        }
        issueRepository.save(issue);

        return "redirect:/issuelist";
    }

    @RequestMapping("/issuelist")
    public String issueList(Model model){
        model.addAttribute("issues", issueRepository.findAll());

        return "issuelist";
    }

    @RequestMapping("/detail/{id}")
    public String showProject(@PathVariable("id") long id, Model model){
        model.addAttribute("project", projectRepository.findById(id).get());
        return "projectShow";
    }

    @RequestMapping("/update/{id}")
    public String updateProject(@PathVariable("id") long id, Model model){
        model.addAttribute("project", projectRepository.findById(id).get());
        return "projectform";
    }

    @RequestMapping("/delete/{id}")
    public String delProject(@PathVariable("id") long id){
        projectRepository.deleteById(id);
        return "index";
    }

    @RequestMapping("/detail_issue/{id}")
    public String showIssue(@PathVariable("id") long id, Model model){
        model.addAttribute("issue", issueRepository.findById(id).get());
        return "issueShow";
    }

    @RequestMapping("/update_issue/{id}")
    public String updateIssue(@PathVariable("id") long id, Model model){
        model.addAttribute("issue", issueRepository.findById(id).get());
        model.addAttribute("projects",projectRepository.findAll());
        return "projectform";
    }

    @RequestMapping("/delete_issue/{id}")
    public String delIssue(@PathVariable("id") long id){
        issueRepository.deleteById(id);
        return "index";
    }
}
