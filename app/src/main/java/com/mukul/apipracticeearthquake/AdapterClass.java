package com.mukul.apipracticeearthquake;


import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mukul.apipracticeearthquake.models.Feature;

import java.util.List;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.ViweHolder> {

    List<Feature> listOfEarthquakes;


    public AdapterClass(List<Feature> listOfEarthquakes) {
        this.listOfEarthquakes = listOfEarthquakes;


    }

    @NonNull

    @Override
    public ViweHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViweHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.details_and_url, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterClass.ViweHolder holder, int position) {

        Feature object = listOfEarthquakes.get(position);


        holder.ID.setText("ID - " + object.getId().toString());
        holder.typeTV.setText("Type - " + object.getProperties().getType().toString());
        holder.mag.setText("Mag - " + object.getProperties().getMag().toString());
        holder.url.setText("URL - " + object.getProperties().getUrl().toString());
        holder.place.setText("Location - " + object.getProperties().getPlace().toString());

        String time = convertDate(object.getProperties().getTime().toString(), "dd/MM/yyyy hh:mm:ss");
        holder.timeTV.setText("Time - " + time);

        holder.detailsurlTV.setText("Details URL - " + object.getProperties().getDetail().toString());
        holder.tsunami.setText("tsumani - " + object.getProperties().getTsunami());
        holder.alertType.setText("Alert Type - " + object.getProperties().getAlert());
        holder.felt.setText("felt - " + object.getProperties().getFelt());
        holder.mmi.setText("mmi - " + object.getProperties().getMmi());
        holder.sig.setText("sig - " + object.getProperties().getSig());
        holder.cdi.setText("cdi - " + object.getProperties().getCdi());
        holder.megType.setText("Meg Type - " + object.getProperties().getMagType());


    }

    @Override
    public int getItemCount() {
        return listOfEarthquakes.size();
    }

    public class ViweHolder extends RecyclerView.ViewHolder {
        TextView mag, place, url, ID, timeTV, typeTV, detailsurlTV, alertType, tsunami, felt, cdi, mmi, sig, megType;

        public ViweHolder(@NonNull View itemView) {
            super(itemView);
            mag = itemView.findViewById(R.id.magnitudeTV);
            place = itemView.findViewById(R.id.placeTV);
            url = itemView.findViewById(R.id.urlTV);
            ID = itemView.findViewById(R.id.ID);
            timeTV = itemView.findViewById(R.id.timeTV);
            typeTV = itemView.findViewById(R.id.type);
            detailsurlTV = itemView.findViewById(R.id.detailsurlTV);
            alertType = itemView.findViewById(R.id.alertTypeTV);
            tsunami = itemView.findViewById(R.id.tsumaniTV);
            felt = itemView.findViewById(R.id.feltTV);
            cdi = itemView.findViewById(R.id.cdiTV);
            mmi = itemView.findViewById(R.id.mmiTV);
            sig = itemView.findViewById(R.id.sigTV);
            megType = itemView.findViewById(R.id.megTypeTV);

        }
    }

    public static String convertDate(String dateInMilliseconds, String dateFormat) {
        return DateFormat.format(dateFormat, Long.parseLong(dateInMilliseconds)).toString();
    }


}
