package info.erwandy.dicodingmynotesapp.adapter;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;

import info.erwandy.dicodingmynotesapp.CustomOnItemClickListener;
import info.erwandy.dicodingmynotesapp.FormAddUpdateActivity;
import info.erwandy.dicodingmynotesapp.R;
import info.erwandy.dicodingmynotesapp.entity.Note;

import static info.erwandy.dicodingmynotesapp.db.DatabaseContract.CONTENT_URI;

/**
 * Created by Nursing Bank IT Dept on 3/22/2018.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

//    private LinkedList<Note> listNotes;
    private Cursor listNotes;
    private Activity activity;

    public NoteAdapter(Activity activity) {
        this.activity = activity;
    }

//    public LinkedList<Note> getListNotes() {
//        return listNotes;
//    }

    public void setListNotes(Cursor listNotes) {
        this.listNotes = listNotes;
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        final Note note = getItem(position);
        holder.tvTitle.setText(note.getTitle());
        holder.tvDate.setText(note.getDate());
        holder.tvDescription.setText(note.getDescription());

        holder.cvNote.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Intent intent = new Intent(activity, FormAddUpdateActivity.class);
//                intent.putExtra(FormAddUpdateActivity.EXTRA_POSITION, position);
//                intent.putExtra(FormAddUpdateActivity.EXTRA_NOTE, getListNotes().get(position));
                Uri uri = Uri.parse(CONTENT_URI+"/"+note.getId());
                intent.setData(uri);

                activity.startActivityForResult(intent, FormAddUpdateActivity.REQUEST_UPDATE);
            }
        }));
    }

    @Override
    public int getItemCount() {
//        return getListNotes().size();
        if (listNotes == null) return 0;
        return listNotes.getCount();
    }

    private Note getItem(int position){
        if (!listNotes.moveToPosition(position)) {
            throw new IllegalStateException("Position invalid");
        }
        return new Note(listNotes);
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvDescription, tvDate;
        CardView cvNote;

        public NoteViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView)itemView.findViewById(R.id.tv_item_title);
            tvDescription = (TextView)itemView.findViewById(R.id.tv_item_description);
            tvDate = (TextView)itemView.findViewById(R.id.tv_item_date);
            cvNote = (CardView)itemView.findViewById(R.id.cv_item_note);
        }
    }
}
