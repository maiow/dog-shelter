package com.redpine.favorites.di

import com.redpine.api.Api
import com.redpine.core.domain.AuthDialogPrefs

interface FavoritesDependencies {
 val api:Api
 val authDialogPrefs: AuthDialogPrefs
}