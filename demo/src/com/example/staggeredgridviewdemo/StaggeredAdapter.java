package com.example.staggeredgridviewdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.ListAdapter;
import com.example.staggeredgridviewdemo.loader.ImageLoader;
import com.example.staggeredgridviewdemo.views.ScaleImageView;
import io.github.eddieringle.android.libs.staggeredgridview.StaggeredGridView;

public class StaggeredAdapter extends ArrayAdapter<String> implements ListAdapter {

	private ImageLoader mLoader;

	public StaggeredAdapter(Context context, int textViewResourceId,
			String[] objects) {
		super(context, textViewResourceId, objects);
		mLoader = new ImageLoader(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder;

		if (convertView == null) {
			LayoutInflater layoutInflator = LayoutInflater.from(getContext());
			convertView = layoutInflator.inflate(R.layout.row_staggered_demo,
					null);
			holder = new ViewHolder();
			holder.imageView = (ScaleImageView) convertView .findViewById(R.id.imageView1);
			convertView.setTag(holder);
		}

		holder = (ViewHolder) convertView.getTag();

		StaggeredGridView.LayoutParams lp = (StaggeredGridView.LayoutParams) convertView.getLayoutParams();
		if (lp == null) {
			lp = new StaggeredGridView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT);
		}
		if(position == 2) {
			lp.span = 2;
		} else {
			lp.span = 1;
		}
		convertView.setLayoutParams(lp);

		mLoader.DisplayImage(getItem(position), holder.imageView);

		return convertView;
	}

	static class ViewHolder {
		ScaleImageView imageView;
	}
}
