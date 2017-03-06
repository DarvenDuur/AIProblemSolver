
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package TestJug;

import java.util.logging.Level;
import java.util.logging.Logger;

import AIproblemSolver.AbstractAction;
import AIproblemSolver.Node;

/**
 *
 * @author darven
 * @param <TState>
 * @param <<error>>
 */
public class JugAction extends AbstractAction<JugState> {
    public static final String[] POSSIBLE_ACTIONS = new String[] {
        "fillA", "fillB", "emptyA", "emptyB", "transferAToB", "transferBToA"
    };
    private final int            index;

    public JugAction(int index) {
        this.index = index % POSSIBLE_ACTIONS.length;
    }

    public JugAction(String action) {
        int tempIndex = -1;

        for (int i = 0; i < POSSIBLE_ACTIONS.length; i++) {
            if (POSSIBLE_ACTIONS[i].equals(action)) {
                tempIndex = i;
            }
        }

        if (tempIndex == -1) {
            tempIndex = 0;
        }

        this.index = tempIndex;
    }

    @Override
    public Node apply(JugState state) {
        try {
            JugState stateCopy = (JugState) state.clone();
            int      cost;

            switch (this.index) {
            case 0 :    // fillA
                cost = stateCopy.getBucketAMax() - stateCopy.getBucketACurrent();
                stateCopy.setBucketACurrent(stateCopy.getBucketAMax());
                stateCopy.addCost(cost);

                break;

            case 1 :    // fillB
                cost = stateCopy.getBucketBMax() - stateCopy.getBucketBCurrent();
                stateCopy.setBucketBCurrent(stateCopy.getBucketBMax());
                stateCopy.addCost(cost);

                break;

            case 2 :    // emptyA
                stateCopy.setBucketACurrent(0);

                break;

            case 3 :    // emptyB
                stateCopy.setBucketBCurrent(0);

                break;

            case 4 :    // transferAToB
                int sum = stateCopy.getBucketACurrent() + stateCopy.getBucketBCurrent();

                // if the sum of the two contents fit in B, empty A to B
                if (sum < stateCopy.getBucketBMax()) {
                    stateCopy.setBucketACurrent(0);
                    stateCopy.setBucketBCurrent(sum);

                    // if the sum of the two contents do not fit in B, fill B and let
                    // the leftover in A
                } else {
                    stateCopy.setBucketBCurrent(stateCopy.getBucketBMax());
                    stateCopy.setBucketACurrent(sum - stateCopy.getBucketBMax());
                }

                break;

            case 5 :    // transferBToA
                sum = stateCopy.getBucketBCurrent() + stateCopy.getBucketACurrent();

                // if the sum of the two contents fit in A, empty B to A
                if (sum < stateCopy.getBucketAMax()) {
                    stateCopy.setBucketBCurrent(0);
                    stateCopy.setBucketACurrent(sum);

                    // if the sum of the two contents do not fit in A, fill A and let
                    // the leftover in B
                } else {
                    stateCopy.setBucketACurrent(stateCopy.getBucketAMax());
                    stateCopy.setBucketBCurrent(sum - stateCopy.getBucketAMax());
                }

                break;
            }

            return new Node(this, stateCopy);
        } catch (CloneNotSupportedException ex) {
            System.out.println("CloneNotSupportedException");

            return null;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof JugAction) {
            return ((JugAction) obj).index == this.index;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return POSSIBLE_ACTIONS[index];
    }

    /**
     * @return the index
     */
    public int getIndex() {
        return index;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
