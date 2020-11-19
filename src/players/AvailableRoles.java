package players;

import java.util.ArrayList;
import java.util.List;

import gameLogic.PlayerRole;

public class AvailableRoles {

	
		private static AvailableRoles theAvailableRoles;
		private List<PlayerRole> availableRoles;
		
		private AvailableRoles() {
			this.availableRoles= new ArrayList<PlayerRole>();
			
			availableRoles.add(Diver);
			
		}
}
