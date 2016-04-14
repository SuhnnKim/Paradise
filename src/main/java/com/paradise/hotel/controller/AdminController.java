package com.paradise.hotel.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.paradise.hotel.entity.Room;
import com.paradise.hotel.service.RoomHandler;

@Controller
public class AdminController {
	
	//@Autowired
	RoomHandler roomHand = new RoomHandler();	
	
	@RequestMapping("/admin")
	 public ModelAndView listRoom() {
				
		List<Room> roomList = roomHand.getAllRooms();		
		
		return new ModelAndView("admin", "roomList", roomList);
	 }
	
	@RequestMapping(value="deleteRoom")
	public String deleteRoom(@RequestParam("id") int id) {
		try {
			Room roomToDelete = roomHand.getRoomById(id);
			roomHand.deleteRoom(roomToDelete);
			
		} catch ( Exception ex) {
			ex.printStackTrace();
		}
		return "admin";
	}
	
	@RequestMapping(value="editRoom")
	public String editRoom(
			@RequestParam("id") int id,
			@RequestParam ("roomType") String roomType,
			@RequestParam("bedType") String bedType,
            @RequestParam ("image") String imageTitle,
            @RequestParam ("price") String price,
            @RequestParam ("description") String description,
            @RequestParam("amen") String[] amenities) {
		
		try {
			Room roomToEdit = roomHand.getRoomById(id);
			roomToEdit.setRoomType(roomType);
			roomToEdit.setBedType(bedType);
			roomToEdit.setImageTitle(imageTitle);
			BigDecimal decPrice = new BigDecimal(price);
			roomToEdit.setPrice(decPrice);
			roomToEdit.setDescription(description);
			for (String a : amenities){
	    		System.out.println(a);
	    		if (a.equals("Breakfast")){ roomToEdit.setBreakfast((byte) 1); }
	    			
	    		if (a.equals("Dinner")){ roomToEdit.setDinner((byte)1); }
	    			
	    		if (a.equals("Spa")){ roomToEdit.setSpa((byte)1); }
	    		
	    		roomHand.updateRoom(roomToEdit);
	    }
						
		} catch (Exception ex)  {
			ex.printStackTrace();
		}
		
		return "admin";
	}
	
	
	@RequestMapping(value="/newRoom")
	public String newRoom(@RequestParam ("description") String description, 
			@RequestParam ("roomType") String roomType,
			@RequestParam("bedType") String bedType,
            @RequestParam ("image") String imageTitle,
            @RequestParam ("price") String price,
    		    @RequestParam("amen") String[] amenities) {			
		
		try{
			Room newRoom = new Room();
		 	newRoom.setRoomType(roomType);
		    newRoom.setDescription(description);
		    newRoom.setBedType(bedType);
		    newRoom.setImageTitle(imageTitle);
		    BigDecimal decPrice = new BigDecimal(price);
		    newRoom.setPrice(decPrice);

		    for (String a : amenities){
		    		System.out.println(a);
		    		if (a.equals("Breakfast")){ newRoom.setBreakfast((byte) 1); }
		    			
		    		if (a.equals("Dinner")){ newRoom.setDinner((byte)1); }
		    			
		    		if (a.equals("Spa")){ newRoom.setSpa((byte)1); }
		    }
		    		    		
			roomHand.addRoom(newRoom);
								
		} catch (Exception ex) {
			ex.printStackTrace();
		}
			    
		return "admin";
	}
		
	
}