CREATE TABLE IF NOT EXISTS public.customers (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name TEXT,
    email TEXT UNIQUE NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS public.activities (
    id BIGSERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    why_fits TEXT NOT NULL,
    first_step TEXT NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS public.journal_entries (
    id BIGSERIAL PRIMARY KEY,
    customer_id UUID NOT NULL
        REFERENCES public.customers(id)
        ON DELETE CASCADE,
    content TEXT NOT NULL,
    entry_date DATE NOT NULL DEFAULT CURRENT_DATE,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS public.user_stats (
    customer_id UUID PRIMARY KEY
        REFERENCES public.customers(id)
        ON DELETE CASCADE,
    total_points INTEGER NOT NULL DEFAULT 0,
    last_mission_reset DATE,
    journal_mission_completed BOOLEAN NOT NULL DEFAULT FALSE,
    goal_mission_completed BOOLEAN NOT NULL DEFAULT FALSE,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

ALTER TABLE public.customers ENABLE ROW LEVEL SECURITY;
ALTER TABLE public.activities ENABLE ROW LEVEL SECURITY;
ALTER TABLE public.journal_entries ENABLE ROW LEVEL SECURITY;
ALTER TABLE public.user_stats ENABLE ROW LEVEL SECURITY;

DROP POLICY IF EXISTS "Users can view their own profile"
ON public.customers;

CREATE POLICY "Users can view their own profile"
ON public.customers
FOR SELECT
USING (true);

DROP POLICY IF EXISTS "Users can update their own profile"
ON public.customers;

CREATE POLICY "Users can update their own profile"
ON public.customers
FOR UPDATE
USING (true)
WITH CHECK (true);

DROP POLICY IF EXISTS "Users can view their own journal"
ON public.journal_entries;

CREATE POLICY "Users can view their own journal"
ON public.journal_entries
FOR SELECT
USING (true);

DROP POLICY IF EXISTS "Users can insert their own journal"
ON public.journal_entries;

CREATE POLICY "Users can insert their own journal"
ON public.journal_entries
FOR INSERT
WITH CHECK (true);

DROP POLICY IF EXISTS "Users can update their own journal"
ON public.journal_entries;

CREATE POLICY "Users can update their own journal"
ON public.journal_entries
FOR UPDATE
USING (true)
WITH CHECK (true);

DROP POLICY IF EXISTS "Users can delete their own journal"
ON public.journal_entries;

CREATE POLICY "Users can delete their own journal"
ON public.journal_entries
FOR DELETE
USING (true);

DROP POLICY IF EXISTS "Users can view their own stats"
ON public.user_stats;

CREATE POLICY "Users can view their own stats"
ON public.user_stats
FOR SELECT
USING (true);

DROP POLICY IF EXISTS "Users can update their own stats"
ON public.user_stats;

CREATE POLICY "Users can update their own stats"
ON public.user_stats
FOR UPDATE
USING (true)
WITH CHECK (true);

DROP POLICY IF EXISTS "Users can insert their own stats"
ON public.user_stats;

CREATE POLICY "Users can insert their own stats"
ON public.user_stats
FOR INSERT
WITH CHECK (true);

DROP POLICY IF EXISTS "Authenticated users can read activities"
ON public.activities;

CREATE POLICY "Authenticated users can read activities"
ON public.activities
FOR SELECT
USING (true);