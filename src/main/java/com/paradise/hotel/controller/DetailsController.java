package com.paradise.hotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DetailsController {
	 @RequestMapping(value="/details")
	  public String welcome() {
		return "roomDetails";
	 	}

}