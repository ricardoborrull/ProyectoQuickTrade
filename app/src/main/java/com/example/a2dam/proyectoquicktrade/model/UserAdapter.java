package com.example.a2dam.proyectoquicktrade.model;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a2dam.proyectoquicktrade.R;

import java.util.List;


public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<Usuario> items;

    public UserAdapter(List<Usuario> items) {
        this.items = items;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.element, viewGroup, false);
        return new UserViewHolder(v);
    }

    @Override
    public void onBindViewHolder(UserViewHolder viewHolder, int i) {
        Usuario item = items.get(i);
        viewHolder.bindUsuario(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView user, nombre, apellidos;
                //correo;

        public UserViewHolder(View v) {
            super(v);
            user = (TextView) v.findViewById(R.id.user);
            nombre = (TextView) v.findViewById(R.id.nombre);
            apellidos = (TextView) v.findViewById(R.id.apellidos);
            //correo = (TextView) v.findViewById(R.id.email);
        }

        public void bindUsuario(Usuario item){
            user.setText(item.getUser().toUpperCase());
            nombre.setText(item.getNombre());
            apellidos.setText(item.getApellidos());
            //correo.setText(item.getCorreo());

        }
    }
}
