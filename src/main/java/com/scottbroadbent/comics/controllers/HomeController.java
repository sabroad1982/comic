package com.scottbroadbent.comics.controllers;



import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.scottbroadbent.comics.models.Collector;
import com.scottbroadbent.comics.models.Share;
import com.scottbroadbent.comics.models.Deal;
import com.scottbroadbent.comics.services.CollectorService;
import com.scottbroadbent.comics.services.DealService;
import com.scottbroadbent.comics.services.ShareService;
import com.scottbroadbent.comics.validators.CollectorValidator;


@Controller


public class HomeController {
	
	@Autowired
	private CollectorService collectorService;
	
	@Autowired
	private ShareService shareService;

	
	@Autowired
	private DealService dealService;
	
	@Autowired
	private CollectorValidator collectorValidator;
	
	public HomeController(CollectorService collectorService, CollectorValidator collectorValidator, ShareService shareService) {
		this.collectorService= collectorService;
		this.collectorValidator=collectorValidator;
		this.shareService=shareService;

	}
	
	@RequestMapping("/")
	public String index(@ModelAttribute("collector") Collector collector) {
		return "index.jsp";
	}
	
	
	@PostMapping("/registration")
	public String registerCollector(@Valid @ModelAttribute("collector") Collector collector, BindingResult result, HttpSession session) {
		collectorValidator.validate(collector, result);
		if(result.hasErrors()) {
			return "index.jsp";
		}
		else {
			Collector newCollector=collectorService.registerCollector(collector);
			session.setAttribute("collectorId", newCollector.getId());
			return "redirect:/home";
		}
	}
	
	
	@PostMapping("/login")
	public String loginCollector(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
		if(collectorService.authenticateCollector(email, password)) {
			Collector collector=collectorService.findByEmail(email);
			session.setAttribute("collectorId", collector.getId());
			return "redirect:/home";
		}
		else {
		redirectAttributes.addFlashAttribute("error", "*Invalid Email/Password*");
		return "redirect:/";
	}
	}
	
	
	@GetMapping("/collector/edit/{id}")
	private String edit(@Valid @PathVariable("id") Long id, @ModelAttribute("editedCollector") Collector collector,Model model, HttpSession session) {
		if(session.getAttribute("collectorId") !=null) {
		Collector editCollector=collectorService.findCollectorById(id);
		Collector collector1=collectorService.findCollectorById((Long) session.getAttribute("collectorId"));
		model.addAttribute("collector", collector1);
		model.addAttribute("editCollector", editCollector);
		model.addAttribute("collectorLoggedIn",(Long)session.getAttribute("collectorId"));
		return "edit.jsp";
	} else {
		return "redirect:/home";
	}
	}
	
	
	@PostMapping("/collector/update/{id}")
	public String update(@PathVariable("id") Long id, @Valid @ModelAttribute("editedCollector") Collector collector, BindingResult result, Model model, HttpSession session) {
		if (result.hasErrors()) {
			Collector editCollector=collectorService.findCollectorById(id);
			model.addAttribute("editCollector", editCollector);
			model.addAttribute("collector",collector);
			model.addAttribute("collectorId", collector.getId());
			model.addAttribute("collectorLoggedIn",(Long)session.getAttribute("collectorId"));
			return "edit.jsp";
		} else {
			Collector newCollector = collectorService.updateCollector(id, collector.getFirstName(), collector.getLastName(), collector.getEmail(),collector.getState(),collector.getFav(),collector.getPassword(),collector.getPasswordConfirmation());
			return "redirect:/home";
		}
	}
	
	
	@GetMapping("/home")
	public String dashboard(@ModelAttribute("newShare") Share thisShare, Collector collector, Model model, HttpSession session,BindingResult result) {
		if(session.getAttribute("collectorId") !=null) {
			List<Share> shares = this.shareService.allShares();
			Collector collector1=collectorService.findCollectorById((Long) session.getAttribute("collectorId"));
			model.addAttribute("shares", shares);
			model.addAttribute("collector", collector1);
			session.setAttribute("collectorId", collector1.getId());
			session.setAttribute("collector", collector1.getId());
			model.addAttribute("collectorLoggedIn",(Long)session.getAttribute("collectorId"));
			return "comicwall.jsp";
		}
		return "redirect:/home";
	}
	
	
	@GetMapping("/collector/{collectorId}")
	public String profile(@PathVariable("collectorId") Long id,Share share, Collector collector, Model model, HttpSession session) {
		Collector collector2=collectorService.findCollectorById((id));
		Collector collector1=collectorService.findCollectorById((Long) session.getAttribute("collectorId"));
		List<Share> shares = this.shareService.allShares();
		model.addAttribute("collector", collector2);
		model.addAttribute("share",share);
		model.addAttribute("shares",shares);
		model.addAttribute("collectorLoggedIn",(Long)session.getAttribute("collectorId"));
		session.setAttribute("collectorId", collector1.getId());
		session.setAttribute("collector", collector1.getId());
		return "profile.jsp";
	}
	
	
	@GetMapping("/collector/user")
	public String profileHome(Share share, Collector collector, Model model, HttpSession session) {
		Collector collector1=collectorService.findCollectorById((Long) session.getAttribute("collectorId"));
		List<Share> shares = this.shareService.allShares();
		model.addAttribute("collector", collector1);
		model.addAttribute("share",share);
		model.addAttribute("shares",shares);
		model.addAttribute("collectorLoggedIn",(Long)session.getAttribute("collectorId"));
		session.setAttribute("collector", collector1.getId());
		return "profile.jsp";
	}
	
	
	@PostMapping("/share/create")
	public String create(@Valid @ModelAttribute("newShare") Share share, BindingResult result,HttpSession session,Collector collector) {
		if (result.hasErrors()) {
			return "comicwall.jsp";
		} 
		else {
			Share newShare = shareService.createShare(share);
			return "redirect:/home";
		}
	}
	
	
	@PostMapping("/{shareId}/deal/create")
	public String createDeal(@Valid @ModelAttribute("newDeal") Deal deal, BindingResult result, HttpSession session,@PathVariable("shareId") Long shareId, Collector collector, Model model) {
		if (result.hasErrors()) {
			Share share = shareService.getOneShare(shareId);
			model.addAttribute("collector",collector);
			model.addAttribute("collectorId", collector.getId());
			return "redirect:/" + collector.getId() + "/share/" + share.getId();
		} else {
			
			Share share = shareService.getOneShare(shareId);
			List<Deal> deals = this.dealService.allDeals();
			Deal newDeal = dealService.createDeal(deal);
			model.addAttribute("deals", deals);
			model.addAttribute("deal", deal);	
			model.addAttribute("collector",collector);
			model.addAttribute("collectorId", collector.getId());	
			return "redirect:/" + collector.getId() + "/share/" + share.getId();
		}
	}
	
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	
	@GetMapping("/deal/{shareId}")
	public String theDealDashboard(@PathVariable("shareId") Long shareId, @ModelAttribute("newDeal") Deal theDeal,  Collector collector, Model model, HttpSession session,BindingResult result) {
		Share share = shareService.getOneShare(shareId);
		Collector collector1=collectorService.findCollectorById((Long) session.getAttribute("collectorId"));
		List<Deal> deals = this.dealService.allDeals();
		model.addAttribute("collector",collector1);
		model.addAttribute("share", share);
		model.addAttribute("collectorLoggedIn",(Long)session.getAttribute("collectorId"));
		model.addAttribute("deals", deals);
		session.setAttribute("collectorId", collector1.getId());
		session.setAttribute("collector", collector1.getId());
		session.setAttribute("shareId", share.getId());
		session.setAttribute("share",share.getId());
		return "dealwall.jsp";
	}
	
	
	@GetMapping("/{collectorId}/share/{shareId}")
	public String LetMakeADeal(@PathVariable("shareId") Long shareId, @ModelAttribute("newDeal") Deal theDeal, Collector collector, Model model, HttpSession session, BindingResult result) {
		Share share = shareService.getOneShare(shareId);
		Collector collector1=collectorService.findCollectorById((Long) session.getAttribute("collectorId"));
		List<Deal> deals = this.dealService.allDeals();
		List<Share> shares = this.shareService.allShares();
		model.addAttribute("collector",collector1);
		model.addAttribute("share", share);
		model.addAttribute("shareId", share.getId());
		model.addAttribute("collectorLoggedIn",(Long)session.getAttribute("collectorId"));
		model.addAttribute("deals", deals);
		model.addAttribute("shares", shares);
		session.setAttribute("collectorId", collector1.getId());
		session.setAttribute("collector", collector1.getId());
		session.setAttribute("shareId", share.getId());
		return "dealwall.jsp";
	}

	
	@GetMapping("/share/new")
	public String projectIndex(@ModelAttribute("newShare") Share share) {
		return "comicwall.jsp";
	}
	
	
	@GetMapping("/share/delete/{collectorId}/{shareId}")
	private String deleteTheShare(@PathVariable("shareId") Long shareId, Share share, Collector collector, Deal deal, Model model) {
		if (deal != null) {
			shareService.deleteShare(shareId);
		return "redirect:/home";
	} else {
		shareService.deleteShare(shareId);
		return "redirect:/home";
		}
	}
	
	
	@GetMapping("/share/delete/{shareId}")
	private String deleteShare(@PathVariable("shareId") Long shareId, Share share, Collector collector, Deal deal, Model model) {
		if (deal != null) {
			shareService.deleteShare(shareId);
		return "redirect:/home";
	} else {
		
		
		shareService.deleteShare(shareId);
		return "redirect:/home";
		}
	}

	
	@GetMapping("/deal/delete/{collectorId}/{shareId}/{id}")
	private String deleteDeal(@PathVariable("id") Long id, Collector collector, Share share, Model model) {
		dealService.deleteDeal(id);
		model.addAttribute("collector",collector);
		model.addAttribute("collectorId", collector.getId());
		return "redirect:/home";
	}
	
	
	@GetMapping("/deal/new")
	public String Deal(@ModelAttribute("newDeal") Deal deal) {
		return "dealwall.jsp";
	}
	
	
	@GetMapping("/shares/seen/{shareId}")
	public String Share(@PathVariable("shareId") Long shareId, HttpSession session) {
		Long collectorId = (Long) session.getAttribute("collectorId");
		Collector collector =  collectorService.findCollectorById(collectorId);
		Share share = shareService.getOneShare(shareId);
		shareService.seenShare(share, collector);
		return "redirect:/home";
	}	
	
	
	@GetMapping("/shares/unseen/{shareId}")
	public String unSeen(@PathVariable("shareId") Long shareId, HttpSession session) {
		Long collectorId= (Long) session.getAttribute("collectorId");
		Collector collector =  collectorService.findCollectorById(collectorId);
		Share share = shareService.getOneShare(shareId);
		shareService.unSeenShare(share, collector);
		return "redirect:/home";
	}

	
	@GetMapping("/{shareId}/deal")
	public String dealDashboard(@ModelAttribute("newDeal") @PathVariable("id") Long shareId, Deal theDeal,  Share share, Collector collector, Model model, HttpSession session, BindingResult result) {
		Share share1 = shareService.getOneShare(shareId);
		Collector collector1=collectorService.findCollectorById((Long) session.getAttribute("collectorId"));
		List<Share> shares1 = this.shareService.allShares();
		List<Deal> deals = this.dealService.allDeals();
		model.addAttribute("collector",collector);
		model.addAttribute("share", share);
		model.addAttribute("collectorLoggedIn",(Long)session.getAttribute("collectorId"));
		model.addAttribute("shares", shares1);
		model.addAttribute("deals", deals);
		session.setAttribute("collectorId", collector1.getId());
		session.setAttribute("collector", collector1.getId());
		return "dealwall.jsp";
	}

	
	@GetMapping("/collector/share/{dealId}")
	public String dealDeal(@PathVariable("id") Long dealId, @ModelAttribute("newDeal") Deal deal,  Collector collector, Model model, HttpSession session) {
		List<Deal> deals = this.dealService.allDeals();
		Deal newDeal = dealService.createDeal(deal);
		model.addAttribute("deals", deals);
		model.addAttribute("deal", deal);		
		Collector collector1=collectorService.findCollectorById((Long) session.getAttribute("collectorId"));
		List<Share> shares1 = this.shareService.allShares();
		model.addAttribute("collector",collector);
		model.addAttribute("collectorLoggedIn",(Long)session.getAttribute("collectorId"));
		model.addAttribute("shares", shares1);
		model.addAttribute("deals", deals);
		model.addAttribute("deal", deal);
		return "dealwall.jsp";
		
	}
}
