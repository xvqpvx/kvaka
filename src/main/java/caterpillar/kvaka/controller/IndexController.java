package caterpillar.kvaka.controller;

import caterpillar.kvaka.service.InventorsInventionsService;
import caterpillar.kvaka.service.impl.InventionServiceImpl;
import caterpillar.kvaka.service.impl.InventorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private final InventionServiceImpl inventionService;
    private final InventorServiceImpl inventorService;
    private final InventorsInventionsService recordsService;

    @Autowired
    public IndexController(InventionServiceImpl inventionService, InventorServiceImpl inventorService, InventorsInventionsService recordsService) {
        this.inventionService = inventionService;
        this.inventorService = inventorService;
        this.recordsService = recordsService;
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("inventorsList", inventorService.findAllInventors());
        model.addAttribute("inventionsList", inventionService.findAllInventions());
        model.addAttribute("inventorsAndInventions", recordsService.getInventorsAndInventions());
        return "index";
    }
}
