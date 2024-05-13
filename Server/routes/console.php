<?php

use App\Http\Services\NotifyReminder;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Schedule;

// TODO: Giảm thời gian thông báo
Schedule::call(new NotifyReminder)->everyFiveMinutes();
