package com.robelseyoum3.robaspotify.ui;

import android.content.Context;
import android.os.Bundle;


import android.support.v4.media.MediaMetadataCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.robelseyoum3.robaspotify.MediaSeekBar;
import com.robelseyoum3.robaspotify.R;



public class MediaControllerFragment extends Fragment implements
        View.OnClickListener
{



    private static final String TAG = "MediaControllerFragment";


    // UI Components
    private TextView mSongTitle;
    private ImageView mPlayPause;
    private MediaSeekBar mSeekBarAudio;

    // Vars
    private IMainActivity mIMainActivity;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_media_controller, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mSongTitle = view.findViewById(R.id.media_song_title);
        mPlayPause = view.findViewById(R.id.play_pause);
        mSeekBarAudio = view.findViewById(R.id.seekbar_audio);


        mPlayPause.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {

    }

    public void setIsPlaying(boolean isPlaying){
        if(isPlaying){
            Glide.with(getActivity())
                    .load(R.drawable.ic_pause_circle_outline_white_24dp)
                    .into(mPlayPause);
        }
        else{
            Glide.with(getActivity())
                    .load(R.drawable.ic_play_circle_outline_white_24dp)
                    .into(mPlayPause);
        }
    }

    public void setMediaItem(MediaMetadataCompat mediaItem){
        mSongTitle.setText(mediaItem.getString(MediaMetadataCompat.METADATA_KEY_TITLE));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mIMainActivity = (IMainActivity) getActivity();
    }



}




















