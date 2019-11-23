/**
 *  This class creates and manages one array of pegs from the game MasterMind.
 *
 *  @author Aarav Noronha
 *  @since 9/30/19
 */

public class PegArray {

	// array of pegs
	private Peg[] pegs;
	private int numberPegs;

	// the number of exact and partial matches for this array
	// as matched against the master.
	// Precondition: these values are valid after getExactMatches() and getPartialMatches()
	//                are called
	private int exactMatches, partialMatches;

	/**
	 *    Constructor - sets pegs in board
	 *    @param numPegs    number of pegs in the array
	 */
	public PegArray(int numPegs) {
        numberPegs = numPegs;
	    pegs = new Peg[numberPegs];
        for(int i= 0; i < numberPegs; i++) {
            pegs[i] = new Peg();
        }
    }

	/**
	 *    Return the peg object
	 *    @param n    The peg index into the array
	 *    @return        the peg object
	 */
	public Peg getPeg(int n) { return pegs[n]; }

	/**
	 *  Finds exact matches between master (key) peg array and this peg array
	 *    Postcondition: field exactMatches contains the matches with the master
	 *  @param master    The master (code) peg array
	 *    @return            The number of exact matches
	 */
	public int getExactMatches(PegArray master) {
		exactMatches = 0;
		for(int i = 0; i < numberPegs;i++){
            if(master.getPeg(i).getLetter() == pegs[i].getLetter()) {
                exactMatches++;
            }
        }
		return exactMatches;
	}

	/**
	 *  Find partial matches between master (key) peg array and this peg array
	 *    Postcondition: field partialMatches contains the matches with the master
	 *  @param master    The master (code) peg array
	 *    @return            The number of partial matches
	 */
	public int getPartialMatches(PegArray master) {

	    partialMatches = 0;
		boolean[] partial = new boolean[numberPegs];
		boolean[] exact = new boolean[numberPegs];
        for(int i = 0; i < numberPegs; i++) {
            partial[i] = false;
			exact[i] = false;
        }
		for(int i = 0; i < numberPegs; i++) {
		    // exclude exact matches from partial computation
		    if(master.getPeg(i).getLetter() == pegs[i].getLetter()) {
                exact[i] = true;
            }
        }
		for(int i = 0; i < numberPegs; i++) {
        	if (!exact[i]) { // don't look for partial matches for pegs with exact matches
				for (int j = 0; j < numberPegs; j++) {
					if (i != j) { // if i=j, exact is true so ignore
						if (!partial[j] && !exact[j]) { // j-th peg already used for previous partial match
							if (master.getPeg(i).getLetter() == pegs[j].getLetter()) {
								partial[j] = true;
								partialMatches++;
								break;
							}
						}
					}
				}
			}
		}

		return partialMatches;
	}

	// Accessor methods
	// Precondition: getExactMatches() and getPartialMatches() must be called first
	public int getExact() { return exactMatches; }
	public int getPartial() { return partialMatches; }

}