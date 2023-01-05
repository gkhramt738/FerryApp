package com.zybooks.ferryapp;

public class TicketCostCalculator {

    // Martha's Vineyard string options
    public enum SpinnerSelect {
        AQUINNAH, CHILMARK, EDGARTOWN, OAKBLUFFS, VINEYARDHAVEN, WESTTISBURY
    }

    private SpinnerSelect mSpinnerSelect;
    private int finalNumberOfTickets;

    public TicketCostCalculator(int ticketsNum, SpinnerSelect level) {
        setLevel(level);
        setTicketQuantity(ticketsNum);
    }

    public void setLevel(SpinnerSelect level) {
        mSpinnerSelect = level;
    }

    // Check to ensure tickets enteres is >= 0, assign values
    public void setTicketQuantity(int tickets) {
        if (tickets >= 0) {
            finalNumberOfTickets = tickets;
        }
    }

    // Select cost based on user town selection, multiply by number of tickets entered
    public int getTotalCost() {
        int specificCostBasedOnTown;
        if (mSpinnerSelect == SpinnerSelect.AQUINNAH) {
            specificCostBasedOnTown = 3;
        } else if (mSpinnerSelect == SpinnerSelect.CHILMARK) {
            specificCostBasedOnTown = 10;
        } else if (mSpinnerSelect == SpinnerSelect.EDGARTOWN) {
            specificCostBasedOnTown = 6;
        } else if (mSpinnerSelect == SpinnerSelect.OAKBLUFFS) {
            specificCostBasedOnTown = 12;
        } else if (mSpinnerSelect == SpinnerSelect.VINEYARDHAVEN) {
            specificCostBasedOnTown = 9;
        } else {
            specificCostBasedOnTown = 4;
        }
        // Return final cost
        return (int) Math.ceil(specificCostBasedOnTown * finalNumberOfTickets);
    }

}