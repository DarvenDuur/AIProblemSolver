
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package TestJug;

import AIproblemSolver.AbstractState;

/**
 *
 * @author darven
 */
public class JugState extends AbstractState implements Cloneable {
    private final int BUCKET_A_MAX;
    private final int BUCKET_B_MAX;
    private final int TARGET;
    private int       bucketACurrent;
    private int       bucketBCurrent;
    private int       cost;

    /**
     * Sets the buckets initial content to 0.
     * @param bucketAMax
     *      maximum volume bucket A can contain
     * @param bucketBMax
     *      maximum volume bucket B can contain
     * @param target
     */
    public JugState(int bucketAMax, int bucketBMax, int target) {
        this.BUCKET_A_MAX   = bucketAMax;
        this.BUCKET_B_MAX   = bucketBMax;
        this.TARGET         = target;
        this.bucketACurrent = 0;
        this.bucketBCurrent = 0;
    }

    /**
     * Sets all the values attatched to the state
     * @param bucketAMax
     *      maximum volume bucket A can contain
     * @param bucketBMax
     *      maximum volume bucket B can contain
     * @param bucketACurrent
     *      volume of bucket A
     * @param bucketBCurrent
     *      volume of bucket B
     */
    public JugState(int bucketAMax, int bucketBMax, int target, int bucketACurrent, int bucketBCurrent) {
        this.BUCKET_A_MAX = bucketAMax;
        this.BUCKET_B_MAX = bucketBMax;
        this.TARGET       = target;
        this.setBucketACurrent(bucketACurrent);
        this.setBucketBCurrent(bucketBCurrent);
    }

    /**
     * @param cost cost to add
     */
    public void addCost(int cost) {
        this.cost += cost;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        JugState clone = (JugState) super.clone();

        return clone;
    }

    /**
     * compare state with other state, in terms of cost
     * @param state
     *      state to compare to
     * @return
     *      0 if state and this have equal cost, or can not be compared;
     *      negative if this costs more than input, positive if this costs less
     */
    @Override
    public int compareTo(AbstractState state) {
        if (state instanceof JugState) {
            return this.cost - ((JugState) state).cost;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof JugState) {
            JugState cast = (JugState) o;

            return this.hashCode() == cast.hashCode();
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;

        hash = 83 * hash + this.BUCKET_A_MAX;
        hash = 83 * hash + this.BUCKET_B_MAX;
        hash = 83 * hash + this.TARGET;
        hash = 83 * hash + this.bucketACurrent;
        hash = 83 * hash + this.bucketBCurrent;

        return hash;
    }

    @Override
    public String toString() {
        return String.format("Bucket A: %s/%s; ",
                             "" + this.getBucketACurrent(),
                             "" + this.getBucketAMax()) + String.format("Bucket B: %s/%s",
                                                                        "" + this.getBucketBCurrent(),
                                                                        "" + this.getBucketBMax()) + "\n -> cost:"
                                                                                                   + this.cost;
    }

    /**
     * @return
     *      current volume contained in bucket A
     */
    public int getBucketACurrent() {
        return bucketACurrent;
    }

    /**
     * @param bucketACurrent
     *      new volume of bucket A
     */
    public void setBucketACurrent(int bucketACurrent) {
        this.bucketACurrent = bucketACurrent;

        if (this.bucketACurrent < 0) {
            this.bucketACurrent = 0;
        } else if (this.bucketACurrent > this.BUCKET_A_MAX) {
            this.bucketACurrent = this.BUCKET_A_MAX;
        }
    }

    /**
     * @return
     *      maximum volume bucket A can contain
     */
    public int getBucketAMax() {
        return BUCKET_A_MAX;
    }

    /**
     * @return
     *      current volume contained in bucket B
     */
    public int getBucketBCurrent() {
        return bucketBCurrent;
    }

    /**
     * @param bucketBCurrent
     *      new volume of bucket B
     */
    public void setBucketBCurrent(int bucketBCurrent) {
        this.bucketBCurrent = bucketBCurrent;

        if (this.bucketBCurrent < 0) {
            this.bucketBCurrent = 0;
        } else if (this.bucketBCurrent > this.BUCKET_B_MAX) {
            this.bucketBCurrent = this.BUCKET_B_MAX;
        }
    }

    /**
     * @return
     *      maximum volume bucket B can contain
     */
    public int getBucketBMax() {
        return BUCKET_B_MAX;
    }

    /**
     * @return the cost
     */
    public int getCost() {
        return cost;
    }

    /**
     * @param cost the cost to set
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public boolean isFinal() {
        return (this.getBucketACurrent() == this.TARGET) || (this.getBucketBCurrent() == this.TARGET);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
