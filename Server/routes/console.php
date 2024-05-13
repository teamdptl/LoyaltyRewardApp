<?php

use App\Http\Services\NotifyReminder;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Schedule;

// TODO: Lặp lại hằng ngày và thông báo nhắc nhở
Schedule::call(new NotifyReminder)->daily();
