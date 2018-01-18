package com.example.a2dam.proyectoquicktrade.model;

import android.net.sip.SipAudioCall;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a2dam.proyectoquicktrade.R;

import java.util.List;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> implements View.OnClickListener {
    private List<Producto> items;
    private View.OnClickListener listener;

    public ProductAdapter(List<Producto> items) {
        this.items = items;
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.producto, viewGroup, false);
        v.setOnClickListener(this);
        return new ProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder viewHolder, int i) {
        Producto item = items.get(i);
        viewHolder.bindProducto(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView nombre, categoria, precio, desc;


        public ProductViewHolder(View v) {
            super(v);
            nombre = (TextView) v.findViewById(R.id.nombre);
            categoria = (TextView) v.findViewById(R.id.categoria);
            precio = (TextView) v.findViewById(R.id.precio);
            desc = (TextView) v.findViewById(R.id.desc);



        }

        public void bindProducto(Producto item) {
            nombre.setText(item.getNombre().toUpperCase());
            categoria.setText(item.getCategoria());
            precio.setText(item.getPrecio()+" €");
            desc.setText(item.getDescripcion());

        }
    }

    @Override
    public void onClick(View view) {
        if(listener != null)
            listener.onClick(view);
    }
}
