package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Admin;
import com.example.demo.model.BankDetails;
import com.example.demo.model.FoodItem;
import com.example.demo.model.Purchase;
import com.example.demo.model.User;
import com.example.demo.service.AdminDAO;
import com.example.demo.service.BankDAO;
import com.example.demo.service.FoodDAO;
import com.example.demo.service.PurchaseDAO;
import com.example.demo.service.UserDAO;

@Controller
public class MainController {
	@Autowired
	AdminDAO adao;
	@Autowired
	FoodDAO fdao;
	@Autowired
	BankDAO bdao;
	@Autowired
	UserDAO udao;
	@Autowired
	PurchaseDAO pdao;
	
	private final String FOLDER_PATH="D:\\img\\";

	
	@RequestMapping("/")
		public ModelAndView homepage(HttpServletRequest request,HttpServletResponse response) {
			ModelAndView mv=new ModelAndView();
			mv.setViewName("index.jsp");
			return mv;
		}
	
	@RequestMapping("/searchfood")
	public ModelAndView searchfood(@Param("keyword") String keyword,HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv=new ModelAndView();
		
		List<FoodItem> list=fdao.findByKeyword(keyword);
		String cname=request.getParameter("cname");
		
		mv.addObject("cname",cname);
		mv.addObject("list",list);
		
		mv.setViewName("showfoodtouser2.jsp");
		return mv;
	}
	
	@RequestMapping("/checkadmin")
	public ModelAndView chkAdmin(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv=new ModelAndView();
		Admin e=new Admin();
		e.setAusername(request.getParameter("adusnm"));
		String user=e.getAusername();
		e.setApassword(request.getParameter("adpass"));
		String pass=e.getApassword();
		String pass1=adao.getPassword(user);
		if(pass1!=null) {
			if(pass.equals(pass1)) {
				
				mv.setViewName("fooddetails.jsp");
			}
			else {
				String msg="Check credentials and try again..Incorrect Password!!";
				mv.addObject("msg",msg);
				mv.setViewName("adminlogin1.jsp");
			}
			
		}
		else {
			String msg="Not a registered admin!!Try Again..";
			mv.addObject("msg",msg);
			mv.setViewName("adminlogin1.jsp");
		}
		return mv;
	}	
	
	@RequestMapping("/checkuser")
	public ModelAndView chkUser(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv=new ModelAndView();
		User u= new User();
		u.setUusername(request.getParameter("uusnm"));
		String username=u.getUusername();
		u.setUpassword(request.getParameter("upass"));
		String password=u.getUpassword();
		String password1=udao.userPwd(username);
		String cname=udao.findtheName(username);
		if(password1!=null) {
			if(password1.equals(password)) {
				String msg="Welcome "+cname+" to Kitchen-Story..Order your food from here!!";
				mv.addObject("msg",msg);
				mv.addObject("cname",cname);
				mv.setViewName("showfoodtouser1.jsp");
			}
			else {
				String msg="Check credentials and try again..Incorrect Password!!";
				mv.addObject("msg",msg);
				mv.setViewName("userlogin1.jsp");
			}
			
		}
		else {
			String msg="Not registered!!Sign Up First..";
			mv.addObject("msg",msg);
			mv.setViewName("userlogin1.jsp");
		}
		
	
	return mv;
	}
	
	@RequestMapping("/insertfood")
	public ModelAndView insertFood(@RequestParam("file") MultipartFile file,HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv=new ModelAndView();
		FoodItem f=new FoodItem();
		f.setFid(Integer.parseInt(request.getParameter("fid")));
		f.setFood_name(request.getParameter("fname"));
		f.setFood_category(request.getParameter("fcat"));
		f.setFood_price(request.getParameter("fprice"));
		String fileinfo=FOLDER_PATH+file.getOriginalFilename();
		f.setFile_path(fileinfo);
		
		FoodItem fd=fdao.insert(f);
		if(fd!=null) {
			String msg="Done!!..Insert a new one?";
			mv.addObject("msg",msg);
			mv.setViewName("fooddetails1.jsp");
		}
		return mv;
		
	}
	
	@RequestMapping("/insertuser")
	public ModelAndView insertUser(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv=new ModelAndView();
		User u= new User();
		u.setU_fname(request.getParameter("ufname"));
		String cname=u.getU_fname();
		u.setU_lname(request.getParameter("ulname"));
		u.setEmail(request.getParameter("uemail"));
		u.setUusername(request.getParameter("uusnm"));
		u.setUpassword(request.getParameter("upass"));
		
		User uu=udao.insert(u);
		if(uu!=null) {
			String msg="Welcome to Kitchen-Story "+cname+"..Order your food from here!!";
			mv.addObject("msg",msg);
			mv.addObject("cname",cname);
			mv.setViewName("showfoodtouser1.jsp");
		}
		return mv;
	}
	
	@RequestMapping("/showfoodadmin")
	public ModelAndView shwadmFood(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv=new ModelAndView();
		List<FoodItem> list=fdao.getAllfood();
		mv.addObject("list",list);
		mv.setViewName("showfoodtoadmin.jsp");
		return mv;
	}
	@RequestMapping("/showfooduser")
	public ModelAndView shwusrFood(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv=new ModelAndView();
		List<FoodItem> list=fdao.getAllfood();
		String cname=request.getParameter("cname");
		System.out.println(cname);
		mv.addObject("list",list);
		mv.addObject("cname",cname);
		mv.setViewName("showfoodtouser.jsp");
		return mv;
	}
	
	@RequestMapping("/deletefood")
	public ModelAndView dltFood(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv=new ModelAndView();
		FoodItem fd=new FoodItem();
		fd.setFid(Integer.parseInt(request.getParameter("fid")));
		String ss=fdao.delete(fd);
		if(ss!=null) {
			String msg="Deleted Successfully!!";
			mv.addObject("msg",msg);
			mv.setViewName("fooddetails1.jsp");
		}
	
	return mv;
	}
	@RequestMapping("/updateadmpass")
	public ModelAndView editPassAdm(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv=new ModelAndView();
		Admin a=new Admin();
		a.setAusername(request.getParameter("adusnm"));
		String usnm=a.getAusername();
		int aid=adao.getId(usnm);
		a.setAid(aid);
		a.setApassword(request.getParameter("adpass"));
		Admin aa=adao.updatePass(a);
		if(aa!=null) {
			String msg="Updated Successfully!!";
			mv.addObject("msg",msg);
			mv.setViewName("adminlogin1.jsp");
		}
		return mv;
		
	}
	
	@RequestMapping("/updatefood")
	public ModelAndView editFood(@RequestParam("file") MultipartFile file,HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv=new ModelAndView();
		FoodItem fd=new FoodItem();
		fd.setFid(Integer.parseInt(request.getParameter("fid")));
		fd.setFood_name(request.getParameter("fname"));
		fd.setFood_category(request.getParameter("fcat"));
		fd.setFood_price(request.getParameter("fprice"));
		String fileinfo=FOLDER_PATH+file.getOriginalFilename();
		fd.setFile_path(fileinfo);
		FoodItem ff=fdao.update(fd);
		if(ff!=null) {
			String msg="Updated Successfully!!";
			mv.addObject("msg",msg);
			mv.setViewName("fooddetails1.jsp");
		
		}
		return mv;
	}
	
	@RequestMapping("/displayalluser")
	public ModelAndView shwUsr(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv=new ModelAndView();
		List<User> list=udao.getAllUsers();
		mv.addObject("list",list);
		mv.setViewName("usernamelist.jsp");
		return mv;
	}
	
	@RequestMapping("/deleteuser")
	public ModelAndView dltUser(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv=new ModelAndView();
		User u=new User();
		u.setUid(Integer.parseInt(request.getParameter("uid")));
		String ss=udao.delete(u);
		if(ss!=null) {
			String msg="Removed The Customer Successfully!!";
			mv.addObject("msg",msg);
			mv.setViewName("fooddetails1.jsp");
		}
	
	return mv;
	}
	
	@RequestMapping("/proceedpay")
	public ModelAndView payment(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv=new ModelAndView();
		long fprice=Long.parseLong(request.getParameter("fprice"));
		BankDetails bd=new BankDetails();
		bd.setBid(Integer.parseInt(request.getParameter("bid")));
		int bid=bd.getBid();
		long balance=bdao.getBalance(bid);
		
		if(balance>fprice) {
			Purchase p=new Purchase();
			p.setName(request.getParameter("cname"));
			p.setFoodname(request.getParameter("fname"));
			p.setFoodpr(request.getParameter("fprice"));
			Purchase pp=pdao.insert(p);
			mv.setViewName("paysuccess.jsp");
		}
		else {
			mv.setViewName("payfail.jsp");
		}
		return mv;
}
	
	@RequestMapping("/purchaserepo")
	public ModelAndView purchaserepo(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv=new ModelAndView();
		List<Purchase> list=pdao.getall();
		mv.addObject("list",list);
		mv.setViewName("purchaselist.jsp");
		return mv;
		
}
}
