/*
 * Copyright (c) 2017, Daniel Gultsch All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its contributors
 * may be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.hjq.demo.chat.widget.quote;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.text.Editable;
import android.text.Spanned;
import android.text.style.URLSpan;
import android.view.View;

import com.hjq.demo.utils.Trace;


@SuppressLint("ParcelCreator")
public class FixedURLSpan extends URLSpan {

	private FixedURLSpan(String url) {
		super(url);
	}

	public static void fix(final Editable editable) {
		for (final URLSpan urlspan : editable.getSpans(0, editable.length() - 1, URLSpan.class)) {
			final int start = editable.getSpanStart(urlspan);
			final int end = editable.getSpanEnd(urlspan);
			editable.removeSpan(urlspan);
			editable.setSpan(new FixedURLSpan(urlspan.getURL()), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		}
	}

	@Override
	public void onClick(View widget) {
		final Uri uri = Uri.parse(getURL());
		final Context context = widget.getContext();
		Trace.d("onClick: " + uri);
	}
}
