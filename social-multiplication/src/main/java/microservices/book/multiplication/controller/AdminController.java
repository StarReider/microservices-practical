package microservices.book.multiplication.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import microservices.book.multiplication.service.AdminService;

@Profile("test")
@RestController
@RequestMapping("/multiplication/admin")
public class AdminController {

	private final AdminService adminService;
	
	public AdminController(final AdminService adminService) {
		this.adminService = adminService;
	}
	
	@PostMapping("/delete-db")
	public ResponseEntity<?> deleteDatabase() {
		adminService.deleteDatabaseContents();
		return ResponseEntity.ok().build();
	}
}