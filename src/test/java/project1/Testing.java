package project1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;
import java.util.List;

import org.junit.Test;

import com.revature.daos.ReimbursementDAO;
import com.revature.daos.UserDAO;
import com.revature.models.LoginDTO;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementDTO;
import com.revature.models.Reimbursement_Status;
import com.revature.models.Users;
import com.revature.services.LoginService;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;

public class Testing {
	public static LoginDTO loginDto;
	public static ReimbursementDTO rdt = new ReimbursementDTO();
	public static UserDAO uDAO = new UserDAO() ;
	public static ReimbursementDAO rDAO = new ReimbursementDAO();
	public static LoginService ls = new LoginService();
	public static UserService userv = new UserService();
	public static ReimbursementService reimbs = new ReimbursementService();
	
	
	public Testing() {
		super();
	}
	

	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testLoginService() {
		LoginDTO t1 = new LoginDTO("Tanisi", "willthiswork?");
		boolean l1 = ls.login(t1);
		assertTrue(l1);
		
		LoginDTO t2 = new LoginDTO("Am_I_here", "booo");
		boolean l2 = ls.login(t2);
		assertFalse(l2);
		
		LoginDTO t3 = new LoginDTO(" ", " ");
		boolean l3 = ls.login(t3);
		assertFalse(l3);
				
	}
	
	@Test
	public void getReimbById() {

		int id = 4;
		
		Reimbursement r = reimbs.findById(id);
		
		assertEquals(r.getReimb_id(), id);
		assertEquals(r.getReimb_status_id(), new Reimbursement_Status (2, "Approved"));
	}
	
	@Test
	public void findAllReimbTest() {
		List<Reimbursement> reimbList = reimbs.findAll();
		assertTrue(reimbList != null);
	}
	
	@Test
	public void getUserById() {
		int id = 2;
		Users u = userv.findById(id);
		assertEquals(u.getUsers_id(), id);
	}
	

	@Test
	public void findAllUsers() {
		List<Users> userList = userv.findAll();
		assertTrue(userList != null);
	}
	
	
	}



