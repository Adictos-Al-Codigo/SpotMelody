package com.jahircelorio.spotmelody;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jahircelorio.spotmelody.Service.model.Data;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomListAdapter2 extends BaseAdapter {

    private Context context = null;
    private List<Data> data = null;

    public CustomListAdapter2(Context context, List<Data> newdata) {
        this.context = context;
        this.data = newdata;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final int ROW_RESOURCE = R.layout.layout_list_view_item2;
        ViewHolder viewHolder = null;

        // ConvertView es null no tiene elementos creados

        if (view==null){
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            view = layoutInflater.inflate(ROW_RESOURCE,viewGroup,false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }else{
            // En caso de que este creado se lo recupera
            viewHolder = (ViewHolder) view.getTag();
        }

        Data newdata = data.get(i);
        try{
            viewHolder.txtid.setText(newdata.getId());
            viewHolder.txtname.setText(newdata.getName());
            Picasso.get().load(newdata.getPicture_medium()).into(viewHolder.imageView);
        }catch (Exception e){
            e.printStackTrace();
        }
        return view;
    }

    public static class ViewHolder{
        TextView txtid = null;
        TextView txtname = null;
        ImageView imageView = null;

        public ViewHolder(View view){
            txtid = view.findViewById(R.id.idgeneros);
            txtname = view.findViewById(R.id.generos);
            imageView = view.findViewById(R.id.imggeneros);
        }
    }
}
