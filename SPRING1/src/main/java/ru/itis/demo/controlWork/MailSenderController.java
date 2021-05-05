package ru.itis.demo.controlWork;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.demo.security.details.UserDetailsImpl;
import ru.itis.demo.services.impls.MessageServiceImpl;

@Controller
@RequiredArgsConstructor
public class MailSenderController {
    private final MailSenderService mailSender;
    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(MailSenderController.class);


    // Если роль изменилась,то роль обновится
    @PreAuthorize("#{user.role = userService.getRole(user.id)}")
    @GetMapping("/getinfo")
    public String getInfo(@AuthenticationPrincipal UserDetailsImpl user, Model model){
        logger.info("User {}",user);
        String text = "Username:"+user.getName()+"\n"+
                        "Email:"+user.getUsername()+"\n"+
                        "Role:"+user.getRole()+"\n"+
                        "State:"+user.getState();
        mailSender.sendMessage("User Imfo",user.getUsername(),text);
        model.addAttribute("name",user.getName());
        model.addAttribute("email",user.getUsername());
        model.addAttribute("role",user.getRole());
        model.addAttribute("state",user.getState());
        return "cw";
    }
}
