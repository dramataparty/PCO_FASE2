/**
 * A class that represents objects that know how to eliminate and accomodate
 * symbols in a one-dimensional array
 * To be used by the class Game of project phase 2
 * @author PCO team
 *
 */
public class Transformer {
	private int blockSize;
	private Symbol nothing;
	
	/**
	 * Constructor.
	 * @param blockSize The minimum size of the sequences to eliminate
	 * @param nothing The symbol corresponding to the empty position
	 *                in sequences to eliminate and accomodate
	 */
	public Transformer(int blockSize, Symbol nothing) {
		this.blockSize = blockSize;
		this.nothing = nothing;
	}
	
	/**
	 * The minimum size of the sequences to eliminate
	 * @return
	 */
	public int blockSize() {
		return this.blockSize;
	}

	/**
	 * The symbol corresponding to the empty position
	 * in sequences to eliminate and accomodate
	 * @return
	 */
	public Symbol nothing() {
		return this.nothing;
	}


	/**
	 * This method searches for contiguous sequences of blockSize() or
	 * more equal elements of a one-dimensional array, and substitute
	 * them by nothing()
	 * @param sequence Array with symbols
	 * @requires sequence != null
	 * @return 0 in case no sequence has been eliminated; 
	 *         the number of eliminated elements otherwise
	 */
	public int eliminateSequence(Symbol[] sequence){
		int i = 0, repetitions = 0;
		Symbol symbolToVerify;
		int eliminated = 0;

		while(i < sequence.length && eliminated == 0){
			repetitions = 0;
			symbolToVerify = sequence[i];

			int k = i;
			while(k < sequence.length && symbolToVerify == sequence[k]){
				k++;
				repetitions++;
			}

			if(repetitions >= this.blockSize && symbolToVerify != this.nothing) {
				eliminated = repetitions;

				for(int j = i; j < k; j++)
					sequence[j] = this.nothing;
			}

			i = k;
		}

		return eliminated;
	}   

	/**
	 * This method accomodates a sequence of symbols in order to "close"
	 * all the "holes" it has, that is, all the intern sequences containing
	 * the nothing() symbol
	 * @param sequence Array with symbols
	 * @requires sequence != null
	 */
	public void accomodate(Symbol[] sequence) {
		boolean needToAcomodate = true;
		while(needToAcomodate) {
			int howManyHoles = 0;
			int firstFilled = 0;
			for(int i = 0 ; i < sequence.length && sequence[i] == this.nothing ; i++) {
				firstFilled++;
			}
			// firstFilled has the position of the first element different from this.nothing
			int firstEmpty = firstFilled;
			for(int i = firstFilled ; i < sequence.length && sequence[i] != this.nothing ; i++) {
				firstEmpty++;
			}
			// firstEmpty has the position of the first element equal to this.nothing
			int l = firstEmpty;
			while(l < sequence.length && sequence[l] == this.nothing) {
				howManyHoles++; 
				l++;
			}
			// howManyHoles has the number of positions in the sequence that are "holes"
			for(int i = firstEmpty + howManyHoles - 1 ; i >= howManyHoles ; i--) {
				sequence[i] = sequence[i - howManyHoles];
			}
			for(int i = firstFilled + howManyHoles - 1 ; i >= 0 ; i--) {
				sequence[i] = this.nothing;
			}
			needToAcomodate = howManyHoles != 0;
		}
	}

}
