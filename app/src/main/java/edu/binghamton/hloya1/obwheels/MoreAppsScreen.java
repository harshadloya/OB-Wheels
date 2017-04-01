package edu.binghamton.hloya1.obwheels;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by hloya on 3/29/2017.
 */

public class MoreAppsScreen extends Fragment
{
    private Button button;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.moreapps_screen, container, false);
        button = (Button) view.findViewById(R.id.moreAppsButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Uri uri = Uri.parse("https://resourcelibrary.stfm.org/HigherLogic/System/DownloadDocumentFile.ashx?DocumentFileKey=72a92740-16aa-6499-f3b1-ae4dc8c2e119&forceDialog=1");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        return view;
    }

}
