package com.vg.sgca;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.TicketViewHolder> {

    private List<Ticket> ticketList;

    public TicketAdapter(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    @NonNull
    @Override
    public TicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_soporte, parent, false);
        return new TicketViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull TicketViewHolder holder, int position) {
        Ticket ticket = ticketList.get(position);
        holder.bind(ticket);
    }

    @Override
    public int getItemCount() {
        return ticketList.size();
    }

    public static class TicketViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewAsunto;
        private final TextView textViewDetalles;

        public TicketViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewAsunto = itemView.findViewById(R.id.textViewAsunto);
            textViewDetalles = itemView.findViewById(R.id.textViewDetalles);
        }

        public void bind(Ticket ticket) {
            textViewAsunto.setText(ticket.getAsunto());
            textViewDetalles.setText(ticket.getDetalles());
        }
    }
}
