/**
 * This type represents the symbols that can be used in the grid
 * and in the pieces of the game
 * @author PCO team
 *
 */
public enum Symbol {
	CARDINAL("#"),
	DOLAR("$"), 
	PERCENT("%"), 
	BANG("!"), 
	AMPERSAND("&"), 
	AT("@"), 
	EMPTY(" ");
	
	private String rep;
	Symbol(String s) {
		this.rep = s;
	}

	public String toString() {
		return this.rep;
	}
}
