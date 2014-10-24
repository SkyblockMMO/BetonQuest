/**
 * 
 */
package pl.betoncraft.betonquest.events;

import java.sql.Timestamp;
import java.util.Date;

import pl.betoncraft.betonquest.BetonQuest;
import pl.betoncraft.betonquest.core.Pointer;
import pl.betoncraft.betonquest.core.QuestEvent;

/**
 * 
 * @author Co0sh
 */
public class JournalEvent extends QuestEvent {

	/**
	 * Constructor method
	 * @param playerID
	 * @param instructions
	 */
	public JournalEvent(String playerID, String instructions) {
		super(playerID, instructions);
		BetonQuest.getInstance().getJournal(playerID).addPointer(new Pointer(instructions.split(" ")[1], new Timestamp(new Date().getTime())));
	}
	
}
