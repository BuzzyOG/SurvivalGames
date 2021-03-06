/**
 * Name: MultiAction.java Edited: 20 January 2014
 *
 * @version 1.0.0
 */

package co.q64.survivalgames.util.items.interfaces;

import java.util.List;

/**
 * The interface Multi action.
 * <p>
 * Does the item have more than one Ability If they do what action sets each one
 * off
 * <p>
 * Select the activators from
 * {@link co.q64.survivalgames.util.items.interfaces.ActionActivator}
 *
 * @author TheCommunitySurvivalGames
 * @version 0.0.1
 */
public interface MultiAction {
	/**
	 * Gets Activators for action 1 An action can have more than one activator
	 *
	 * @return the action 1 activators as a list
	 * @see {@link co.q64.survivalgames.util.items.interfaces.ActionActivator}
	 * *
	 */
	List<ActionActivator> getAction1();

	/**
	 * Gets Activators for action 2 An action can have more than one activator
	 *
	 * @return the action 2 activators as a list
	 * @see {@link co.q64.survivalgames.util.items.interfaces.ActionActivator}
	 */
	List<ActionActivator> getAction2();
}
